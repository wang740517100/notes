package cn.wangkf.monday.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 使用反射实现的
 * Created by stanley.wang on 2020/6/12.
 */
public class ProxyTest {

    public static void main(String[] args) {
        Person person = new Worker();

        // 动态代理处理器 可以随时替换
        InvocationHandler handler = new PersonInvocationHandler<Person>(person);

        Person workProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(),
                new Class<?>[]{Person.class}, handler);
        workProxy.doSomething();
    }



}
