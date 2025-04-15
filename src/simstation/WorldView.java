package simstation;

import mvc.Model;
import mvc.View;

import java.awt.*;
import java.util.Iterator;

public class WorldView extends View {
    public WorldView(Model model) {
        super(model);
        setPreferredSize(new Dimension(World.SIZE, World.SIZE));
    }
    public void paintComponent(Graphics gc){
        super.paintComponent(gc);
        Iterator<Agent> agentIterator = ((World)model).iterator();
        while(agentIterator.hasNext())
            drawAgent(agentIterator.next(), gc);
    }
    public void drawAgent(Agent a, Graphics gc){
        Color oldColor = gc.getColor();
        gc.setColor(Color.RED);
        gc.fillOval(a.xc, a.yc, 10, 10 );
        gc.setColor(oldColor);
    }
}
