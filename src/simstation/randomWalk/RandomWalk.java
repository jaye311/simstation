package simstation.randomWalk;


import mvc.*;
import simstation.*;

class Drunk extends MobileAgent {

    public Drunk() {
        super();
    }

    public void update() throws InterruptedException{
        heading = Heading.random();
        int steps = Utilities.rng.nextInt(20) + 1;
        move(steps);
    }

}


class RandomWalkFactory extends WorldFactory {
    @Override
    public Model makeModel() { return new RandomWalkSimulation(); }
    @Override
    public String getTitle() { return "Random Walks";}
}


