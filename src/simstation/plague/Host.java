package simstation.plague;

import mvc.Utilities;
import simstation.Heading;
import simstation.MobileAgent;
import simstation.World;

public class Host extends MobileAgent {
    private boolean infected;
    private int infectionProbability;
    private int fatalityTime;
    private boolean isFatal;
    private boolean isDead;
    private int timeSinceInfection;

    public Host(boolean infected, int infectionProbability, int fatalityTime, boolean isFatal) {
        super();
        this.infected = infected;
        this.infectionProbability = infectionProbability;
        this.fatalityTime = fatalityTime;
        this.isFatal = isFatal;
        this.isDead = false;
        this.timeSinceInfection = 0;
    }

    public boolean isInfected() {
        return infected;
    }

    public boolean isDead() {
        return isDead;
    }

    @Override
    public void update() throws Exception {
        // Random movement
        int steps = Utilities.rng.nextInt(10) + 1;
        heading = Heading.random();

        move(steps);

        if (infected) {
            timeSinceInfection++;
            
            if (timeSinceInfection >= fatalityTime) {
                if (isFatal) {
                    stop();
                    isDead = true;
                } else {
                    infected = false;
                    timeSinceInfection = 0;
                }
                return;
            }

            Host neighbor = (Host) world.getNeighbor(this, 15);
            if (neighbor != null && !neighbor.isInfected()) {
                if (Utilities.rng.nextInt(100) < infectionProbability) {
                    neighbor.infect();
                }
            }
        }
    }
    
    public void infect() {
        if (!infected) {
            infected = true;
            timeSinceInfection = 0;
        }
    }
}
