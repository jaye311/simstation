package simstation.flocking;

import mvc.Utilities;
import simstation.Heading;
import simstation.MobileAgent;

public class Bird extends MobileAgent {
    private int speed;
    public Bird()  {
        super();
        heading = Heading.random();
        speed = Utilities.rng.nextInt(50) + 1;
    }
    @Override
    public void update() throws Exception {
        move(speed);
        Bird neighbor =(Bird) world.getNeighbor(this, 25);
        if(neighbor == null){
            heading = Heading.random();
            speed = Utilities.rng.nextInt(50) + 1;
        }
        else{
            heading = neighbor.getHeading();
            speed = neighbor.getSpeed();
        }
    }
    public int getSpeed(){
        return speed;
    }
}
