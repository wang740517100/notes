package cn.wangkf.monday.singleton;

/**
 * Created by stanley.wang on 2020/6/12.
 */
public final class LazySingleton {

    private static LazySingleton singleton;

    private LazySingleton() {}

    public static LazySingleton getInstance() {
        if (singleton == null) {
            singleton = new LazySingleton();
        }
        return singleton;
    }
}
