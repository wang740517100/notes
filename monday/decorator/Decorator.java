package cn.wangkf.monday.decorator;

/**
 * Created by stanley.wang on 2020/6/11.
 */
public abstract class Decorator extends Component {

    private Component component = null;

    public Decorator(Component component) {
        this.component = component;
    }
     public void doSomething() {
         if (component != null) {
             component.doSomething();
         }
     }
}
