package simstation.flocking;

import mvc.AppPanel;
import simstation.World;
import simstation.WorldPanel;

public class FlockingSimulation extends World {

    @Override
    public void populate() {
        for(int i = 0; i < 200; i++)
            addAgent(new Bird());
    }

    public static void main(String[] args) {
        AppPanel panel = new WorldPanel(new FlockingFactory());
        panel.display();
    }
}
