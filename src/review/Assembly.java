package review;



 interface Component1 {}

 interface Component2 {}

 interface AbstractFactory {
    public Component1 makeComponent1();
    public Component2 makeComponent2();
}

public class Assembly {
    private Component1 a, b, c;
    private Component2 d, e;
    public Assembly(AbstractFactory factory) {
        a = factory.makeComponent1();
        b = factory.makeComponent1();
        c = factory.makeComponent1();
        d = factory.makeComponent2();
        e = factory.makeComponent2();
    }
    // etc.
}


 class ConcreteComponent1 {}

 class ConcreteComponent2 {}

 class Adapter1 implements Component1 {
    ConcreteComponent1 adaptee = new ConcreteComponent1();
}

 class Adapter2 implements Component2 {
    ConcreteComponent2 adaptee = new ConcreteComponent2();
}


 class ConcreteFactory implements AbstractFactory {
    @Override
    public Component1 makeComponent1() {
        return new Adapter1();
    }

    @Override
    public Component2 makeComponent2() {
        return new Adapter2();
    }
}

class TestAssembly {
    public static void main(String[] args) {
        ConcreteFactory factory = new ConcreteFactory();
        Assembly assembly = new Assembly(factory);
        // etc.
    }
}
