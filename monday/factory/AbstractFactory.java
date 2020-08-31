package cn.wangkf.monday.factory;


/**
 * Created by stanley.wang on 2020/6/12.
 */
public abstract class AbstractFactory {
    abstract Product getProductA();
    abstract Product getProductB();
    abstract Product getProductC();
}

// 具体工厂
class FactoryA extends AbstractFactory {
    @Override
    Product getProductA() {
        return new ConcreteProductA1();
    }
    @Override
    Product getProductB() {
        return new ConcreteProductB1();
    }
    @Override
    Product getProductC() {
        return new ConcreteProductC1();
    }
}
class FactoryB extends AbstractFactory {
    @Override
    Product getProductA() {
        return new ConcreteProductA2();
    }
    @Override
    Product getProductB() {
        return new ConcreteProductB2();
    }
    @Override
    Product getProductC() {
        return new ConcreteProductC2();
    }
}

// 抽象产品: 一类产品，比如饮料、膨化食品
abstract class AbstractProductA extends Product{
    @Override
    abstract void intro();
}
abstract class AbstractProductB extends Product{
    @Override
    abstract void intro();
}
abstract class AbstractProductC extends Product{
    @Override
    abstract void intro();
}

// 具体产品: 比如农夫山泉、怡宝
class ConcreteProductA1 extends AbstractProductA {
    @Override
    void intro() {
        System.out.println("农夫山泉");
    }
}
class ConcreteProductA2 extends AbstractProductA {
    @Override
    void intro() {
        System.out.println("怡宝");
    }
}

// 具体产品: 比如妙脆角、锅巴
class ConcreteProductB1 extends AbstractProductB {
    @Override
    void intro() {
        System.out.println("妙脆角");
    }
}
class ConcreteProductB2 extends AbstractProductB {
    @Override
    void intro() {
        System.out.println("锅巴");
    }
}

// 其它
class ConcreteProductC1 extends AbstractProductC {
    @Override
    void intro() {
        System.out.println("其它1");
    }
}
class ConcreteProductC2 extends AbstractProductC {
    @Override
    void intro() {
        System.out.println("其它2");
    }
}

// 抽象工厂模式 一个工厂对应所有品类产品
class AbstractFactoryTest {
    public static void main(String[] args) {
        AbstractFactory factoryA = new FactoryA();
        factoryA.getProductA().intro();
        factoryA.getProductB().intro();
        factoryA.getProductC().intro();

        AbstractFactory factoryB = new FactoryB();
        factoryB.getProductA().intro();
        factoryB.getProductB().intro();
        factoryB.getProductC().intro();
    }
}
