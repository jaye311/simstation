package simstation.randomWalk;


import mvc.Model;
import mvc.Utilities;
import simstation.Heading;
import simstation.MobileAgent;
import simstation.WorldFactory;

class Drunk extends MobileAgent {

    public Drunk() {
        super();
    }

    public void update() {
        turn(Heading.random());
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


