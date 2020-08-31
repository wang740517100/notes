package cn.wangkf.monday.singleton;

/**
 * Created by stanley.wang on 2020/6/12.
 */
public final class DoubleCheckSingleton {

    // 防止指令重排，导致该实例的读操作在写之前
    private volatile static DoubleCheckSingleton singleton;

    private DoubleCheckSingleton() {}
    
    public static DoubleCheckSingleton getInstance() {
        if (singleton == null) { //减少锁竞争，第一次为null时才会竞争创建单例
            synchronized (DoubleCheckSingleton.class) { // 锁定
                if (singleton == null) { // 判断有没有已经创建好的
                    singleton = new DoubleCheckSingleton();
                }
            }
        }
        return singleton;
    }
}
