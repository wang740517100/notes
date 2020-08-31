package cn.wangkf.monday.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by stanley.wang on 2020/6/12.
 */
public class ProxyTest {

    public static void main(String[] args) {
        Person person = new Worker();

        InvocationHandler handler = new PersonInvocationHandler<Person>(person);
        Person workProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(),
                new Class<?>[]{Person.class}, handler);

        workProxy.doSomething();
    }



}
