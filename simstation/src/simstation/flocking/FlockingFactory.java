package simstation.flocking;

import mvc.Model;
import simstation.WorldFactory;

public class FlockingFactory extends WorldFactory {
    @Override
    public Model makeModel() {
        return new FlockingSimulation();
    }
    @Override
    public String getTitle() { return "Flocking";}
}
