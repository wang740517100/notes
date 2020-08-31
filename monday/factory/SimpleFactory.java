package cn.wangkf.monday.factory;

/**
 * Created by stanley.wang on 2020/6/12.
 */
abstract class Product {
    abstract void intro();
}

// 一种具体产品
class AProduct extends Product {
    @Override
    void intro() {
        System.out.println("A");
    }
}
class BProduct extends Product {
    @Override
    void intro() {
        System.out.println("B");
    }
}
class CProduct extends Product {
    @Override
    void intro() {
        System.out.println("C");
    }
}

// 简单工厂模式 一个工厂对应多个产品
public class SimpleFactory {
    public static Product getProduct(Product product) {
        Product res = null;
        if (product instanceof AProduct) {
            res = new AProduct();
        } else if (product instanceof BProduct) {
            res = new BProduct();
        } else if (product instanceof  CProduct) {
            res = new CProduct();
        }
        return res;
    }
}