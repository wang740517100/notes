package cn.wangkf.monday.decorator;

/**
 * Created by stanley.wang on 2020/6/11.
 */
public class ConcreteDecorator extends Decorator {


    public ConcreteDecorator(Component component) {
        super(component);
    }
    public void addSomething() {
        doing(); //装饰事情
        super.doSomething();
    }
    public void doing() {}
}
