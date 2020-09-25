package cn.wangkf.monday.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理要做的事情定义
 * Created by stanley.wang on 2020/6/12.
 */
public class PersonInvocationHandler<T> implements InvocationHandler {

    private T t;

    public PersonInvocationHandler(T t) {
        this.t = t;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        beforeDoSomething(); // 这个也可以是其它对象的方法，或者是工厂方法
        Object res = method.invoke(t, args);
        afterDoSomething();
        return res;

    }

    public void beforeDoSomething(){}
    public void afterDoSomething(){}



}
