package simstation;

public class ObserverAgent extends Agent {
    public ObserverAgent(World world) {
        super(world);
    }

    @Override
    public void update() {
        world.updateStatistics();
    }
}
