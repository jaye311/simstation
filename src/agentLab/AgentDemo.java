package agentLab;

// divide state by 3 until 0 reached
class Divider extends Agent {
    private int state;
    public Divider(int init) {
        super("Divider");
        state = init;
    }
    public void update() {
        if (state == 0) stop();
        state = state / 3;
        manager.println("state = " + state);
    }
}

// generate all primes: 2, 3, 5, 7, 11, etc.
class PrimeGenerator extends Agent {
    private int nextPrime = 2;
    private int divisor = 2;
    public PrimeGenerator() {
        super("Prime Generator");
    }

    public void update() {
        if (nextPrime <= divisor) {
            // found one
            manager.println("next prime = " + nextPrime);
            nextPrime++;
            divisor = 2;
        } else if (nextPrime % divisor == 0) {
            // start over
            nextPrime++;
            divisor = 2;
        } else {
            divisor++; // keep checking
        }
    }
}

// use partial sums of Taylor series for 4 * arctan(1) to appx pi
class PiApproximator extends Agent {
    private double pi = 0;
    private double k = 1.0;
    private int sign = 1;
    public PiApproximator() {
        super("Pi Approximator");
    }
    public void update() {
        pi = pi + 4 * sign/k;
        sign = -1 * sign;
        k = k + 2;
        manager.println("pi = " + pi);
    }
}

public class AgentDemo {
    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.add(new PiApproximator());
        manager.add(new PrimeGenerator());
        manager.add(new Divider(113));
        manager.controlLoop();
    }
}