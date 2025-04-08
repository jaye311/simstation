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
    public void update(){
        move(speed);
        Bird neighbor =(Bird) world.getNeighbor(this, 15);
        if(neighbor == null){
            heading = Heading.random();
            speed = Utilities.rng.nextInt(20) + 1;
        }
        else{
            turn(neighbor.getHeading());
            speed = neighbor.getSpeed();
        }
    }
    public int getSpeed(){
        return speed;
    }
}
