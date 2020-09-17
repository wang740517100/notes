package cn.wangkf.monday;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by stanley.wang on 2020/8/2.
 *
 */
public class PrintDemo {

    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();
    private Condition conditionD = lock.newCondition();

    private Boolean falgA = true;
    private Boolean falgB = false;
    private Boolean falgC = false;
    private Boolean falgD = false;

    private volatile Integer count = 1;

    public void runA() {
        while (count<100) {
            lock.lock();

            try {
                while (!falgA) {
                    try {
                        conditionA.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                this.executeCount();

                falgA = false;
                falgB = true;
                conditionB.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }



    }

    public void runB() {
        while (count<100) {
            lock.lock();
            try {
                while (!falgB) {
                    try {
                        conditionB.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                this.executeCount();

                falgB = false;
                falgC = true;
                conditionC.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public void runC() {
        while (count<100) {
            lock.lock();
            try {
                while (!falgC) {
                    try {
                        conditionC.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.executeCount();

                falgC = false;
                falgD = true;

                conditionD.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public void runD() {
        while (count<100) {
            lock.lock();
            try {
                while (!falgD) {
                    try {
                        conditionD.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                this.executeCount();

                falgD = false;
                falgA = true;

                conditionA.signal();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private void executeCount() {
        while (count < 100) {
            if (count%3==0 && count%5==0) {
                System.out.println("当前线程:"+ Thread.currentThread().getName() +  "执行:C" + "---" + count++);
                break;
            } else if (count%3==0) {
                System.out.println("当前线程:"+ Thread.currentThread().getName() +  "执行:A" + "---" + count++);
                break;
            } else if (count%5==0) {
                System.out.println("当前线程:"+ Thread.currentThread().getName() +  "执行:B" + "---" + count++);
                break;
            }
            count ++;
        }
    }

    public static void main(String[] args) {
        PrintDemo printDemo = new PrintDemo();

        new Thread(() -> {
            printDemo.runA();
        }, "threadA").start();

        new Thread(() -> {
            printDemo.runB();
        }, "threadB").start();

        new Thread(() -> {
            printDemo.runC();
        }, "threadC").start();

        new Thread(() -> {
            printDemo.runD();
        }, "threadD").start();


    }



}
