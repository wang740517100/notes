package cn.wangkf.monday.singleton;

/**
 * Created by stanley.wang on 2020/6/17.
 */
public enum  EnumSingleton {

    INSTANCE;

    EnumSingleton() {}

    private static EnumSingleton getInstance() {
        return INSTANCE;
    }

}
