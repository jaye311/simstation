package simstation.greed;

import mvc.Model;
import simstation.Agent;
import simstation.WorldView;

import java.awt.*;
import java.util.Iterator;

public class GreedView extends WorldView {

	public GreedView(Model model) {
		super(model);
	}
	
	@Override
	public void paintComponent(Graphics gc) {
		super.paintComponent(gc);
		
		Meadow meadow = (Meadow) model; 
		int patchSize = Patch.patchSize; 
		
		for (int i = 0; i < meadow.dim; i++) {
			for (int j = 0; j < meadow.dim; j++) {
				Patch patch = meadow.getPatch(i, j);
				if (patch != null) {
					int energy = patch.energy; 
					gc.setColor(new Color(0, 255, 0));
					gc.fillRect(i * patchSize, j * patchSize, patchSize, patchSize);
					
					gc.setColor(Color.WHITE);
					gc.drawRect(i*patchSize, j*patchSize, patchSize, patchSize);
				}
				
			}
		}
		
		
		Iterator<Agent> agentIterator = meadow.iterator(); 
		while (agentIterator.hasNext()) {
			drawAgent(agentIterator.next(), gc);
		}
	}
	
	@Override
	public void drawAgent(Agent a, Graphics gc) {
		if (a instanceof Cow) {
			Cow cow = (Cow) a;
			Color oldColor = gc.getColor(); 
			gc.setColor(cow.isDead ? Color.white : Color.RED);
			gc.fillOval(cow.getX(), cow.getY(), 6, 6);
			gc.setColor(oldColor);
		}
	}

}
