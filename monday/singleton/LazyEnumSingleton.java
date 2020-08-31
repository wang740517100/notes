package cn.wangkf.monday.singleton;

/**
 * Created by stanley.wang on 2020/6/17.
 *
 * 枚举实现的懒加载
 */
public class LazyEnumSingleton {

    private LazyEnumSingleton() {}

    private enum EnumHolder{
        INSTANCE;
        LazyEnumSingleton singleton;
        EnumHolder() {
            this.singleton = new LazyEnumSingleton();
        }
        private LazyEnumSingleton getSingleton() {
            return singleton;
        }
    }

    public static LazyEnumSingleton getInstance() {
        return EnumHolder.INSTANCE.getSingleton();
    }

}
