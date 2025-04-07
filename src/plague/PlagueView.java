package plague;

import mvc.Model;
import simstation.Agent;
import simstation.WorldView;
import java.awt.*;

public class PlagueView extends WorldView {
    public PlagueView(Model model) {
        super(model);
    }

    @Override
    public void drawAgent(Agent a, Graphics gc) {
        Color oldColor = gc.getColor();
        Host host = (Host)a;
        gc.setColor(host.isInfected() ? Color.RED : Color.GREEN);
        Dimension point = a.getPoint();
        gc.fillOval(point.width, point.height, 10, 10);
        gc.setColor(oldColor);
    }
} 