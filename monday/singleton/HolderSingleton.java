package cn.wangkf.monday.singleton;

/**
 * Created by stanley.wang on 2020/6/12.
 */
public final class HolderSingleton {

    private HolderSingleton() {}

    private static class Holder {
        private static HolderSingleton singleton = new HolderSingleton();
    }

    public static HolderSingleton getInstance() {
        return Holder.singleton;
    }

}
