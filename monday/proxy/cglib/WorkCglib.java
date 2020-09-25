package cn.wangkf.monday.proxy.cglib;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by stanley.wang on 2020/9/25.
 */
public class WorkCglib implements MethodInterceptor {

    private Object target;

    /**
     * 创建代理对象
     * @param target
     * @return
     */
    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy)
            throws Throwable {
        this.beforeDoSomething();
        methodProxy.invokeSuper(obj, args);
        this.afterDoSomething();
        return null;
    }


    public void beforeDoSomething(){}
    public void afterDoSomething(){}
}
