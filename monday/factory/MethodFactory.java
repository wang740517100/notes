package cn.wangkf.monday.factory;

/**
 * Created by stanley.wang on 2020/6/12.
 */
public abstract class MethodFactory {
    abstract Product getProduct();

}

class AFactory extends MethodFactory {
    @Override
    Product getProduct() {
        return new AProduct();
    }
}
class BFactory extends MethodFactory {
    @Override
    Product getProduct() {
        return new BProduct();
    }
}
class CFactory extends MethodFactory {
    @Override
    Product getProduct() {
        return new CProduct();
    }
}

// 工厂方法模式 一个工厂对应一种产品
class MethodFactoryTest {
    public static void main(String[] args) {
        MethodFactory aFactory = new AFactory();
        aFactory.getProduct().intro();

        MethodFactory bFactory = new BFactory();
        bFactory.getProduct().intro();

        MethodFactory cFactory = new CFactory();
        cFactory.getProduct().intro();
    }
}