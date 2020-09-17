package cn.wangkf.monday;


import java.util.Random;
import java.util.concurrent.locks.StampedLock;

public class StamptedLock {
    private static final StampedLock stampedLock = new StampedLock();


    private static volatile Integer x=0, y=0;

    private static Random random = new Random();

    public static void main(String[] args) {
        //启动 5个线程写计数,95 个线程读计数,
        for (int i = 0; i < 100; i++) {
            if (i % 20 == 0) {
                new Thread(() -> {
                    try {
                        Thread.sleep(random.nextInt(10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " res :" + distanceFromOrigin());
                }).start();
            } else {
                new Thread(() -> {
                    try {
                        Thread.sleep(random.nextInt(10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    move(10, 1);
                }).start();
            }
        }
    }

    public static double distanceFromOrigin() {
        long stamp = stampedLock.tryOptimisticRead(); // 获得一个版本号
        // 注意下面两行代码不是原子操作
        // 假设x,y = (100,200)
        Integer currentX = x;
        // 此处已读取到x=100，但x,y可能被写线程修改为(300,400)
        Integer currentY = y;
        // 此处已读取到y，如果没有写入，读取是正确的(100,200)
        // 如果有写入，读取是错误的(100,400)
        if (!stampedLock.validate(stamp)) { // 检查读后版本号与读前是否一致
            stamp = stampedLock.readLock(); // 获取一个悲观读锁
            try {
                currentX = x;
                currentY = y;
            } finally {
                stampedLock.unlockRead(stamp); // 释放悲观读锁
            }
        }
        return currentX + currentY;
    }

    public static void move(Integer deltaX, Integer deltaY) {
        long stamp = stampedLock.writeLock(); // 获取写锁
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            stampedLock.unlockWrite(stamp); // 释放写锁
        }
    }

}
