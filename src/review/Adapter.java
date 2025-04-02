package review;

public interface Adapter {
    public static void main(String[] args) {

    }
}
class Client {
    private Provider provider;
    // etc.
}
//BASICALLY CHANGES NAMES OF METHODS BECAUSE INTERFACE AND CLIENT REQUIRE DIFFERENT NAMES WHEN INTERFACE HAS IMPLEMENTED STUFF YOU WANT
interface Provider {
    void service1();
    void serice2();
}


class ConcreteProvider {
    public void serviceA() {  }
    public void serviceB() {  }
}

class ProviderAdapter1 extends ConcreteProvider implements Provider {
    public void service1() { serviceA(); }
    public void serice2() { serviceB(); }
}

class ProviderAdapter2 implements Provider {
    private ConcreteProvider adaptee = new ConcreteProvider();
    public void service1() { adaptee.serviceA(); }
    public void serice2() { adaptee.serviceB(); }
}