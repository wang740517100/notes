package cn.wangkf.monday.singleton;

/**
 * Created by stanley.wang on 2020/6/12.
 */
public final class HungrySingleton {

    private static HungrySingleton singleton = new HungrySingleton();

    private HungrySingleton() {}

    public static HungrySingleton getInstance() {
        return singleton;
    }

}
