package cn.wangkf.monday.proxy.cglib;

/**
 *
 * 对指定的目标类生成一个子类，并覆盖其中方法实现增强
 * 因为采用的是继承，所以不能对final修饰的类进行代理
 * Created by stanley.wang on 2020/9/25.
 */
public class CglibTest {

    public static void main(String[] args) {
        WorkCglib workCglib = new WorkCglib();
        Worker worker = (Worker)workCglib.getInstance(new Worker());
        worker.doSomething();
    }

}
