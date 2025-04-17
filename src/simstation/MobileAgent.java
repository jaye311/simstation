package simstation;

public abstract class MobileAgent extends Agent {
    protected Heading heading;
    public MobileAgent(){
        super();
    }

    public void move(int steps) {
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
                xc = xc + World.SIZE;
            else if(xc >= World.SIZE)
                xc = xc - World.SIZE;
            if(yc < 0)
                yc = yc + World.SIZE;
            else if(yc >= World.SIZE)
                yc = yc - World.SIZE;
              
              try {
                Thread.sleep(20);
              } catch (Exception e) {
                System.err.println(e.getMessage());
              }
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
