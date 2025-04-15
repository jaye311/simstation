package simstation.greed;

public class GreedSimulation extends Meadow {

    @Override
    public void populate() {
        for(int i = 0; i < 200; i++)
            addAgent(new Cow());
    }

    public static void main(String[] args) {
        GreedPanel panel = new GreedPanel(new GreedFactory());
        panel.display();
    }
}
