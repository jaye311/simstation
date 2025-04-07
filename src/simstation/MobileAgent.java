package simstation;

public abstract class MobileAgent extends Agent {
    protected Heading heading;
    public MobileAgent(){
        super();
    }
    public MobileAgent(World world) {
        super(world);
    }

    public void move(int steps) throws InterruptedException{
        int i = 0;
        while(i < steps) {
            i++;
            if(heading == Heading.NORTH)
                yc++;
            else if(heading == Heading.SOUTH)
                yc--;
            else if(heading == Heading.EAST)
                xc++;
            else if(heading == Heading.WEST)
                xc--;
            if(xc < 0)
                xc = World.SIZE;
            else if(xc > World.SIZE)
                xc = 0;
            if(yc < 0)
                yc = World.SIZE;
            else if(yc > World.SIZE)
                yc = 0;
            Thread.sleep(20);
            world.changed();
        }
    }
    public void turn(Heading dir){
        heading = dir;
    }
    public Heading getHeading(){
        return heading;
    }
}
