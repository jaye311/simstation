package simstation.greed;

import simstation.Agent;
import simstation.World;

import java.util.*;

import mvc.Utilities; 

public class Meadow extends World{
	
	int waitPenalty = 5;
	int movePenalty = 10; 
	int numCows = 50;
	int patchsize = Patch.patchSize; 
	int dim = World.SIZE/patchsize; 
	
	private Patch[][] patches;
	
	public Meadow() {
		patches = new Patch[dim][dim];
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				patches[i][j] = new Patch(); 
				addAgent(patches[i][j]);
			}
		}
	}
	
	public Patch getPatch(int xc, int yc) {
		if (xc >= 0 && xc < dim && yc >= 0 && yc < dim) {
			return patches [xc][yc];
		}
		return null; 
	}
	
	public void setGrowthRate(int rate) {
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				patches[i][j].growbackrate = rate;
			}
		}
	}
	
	public List<Cow> getCows() {
		List<Cow> result = new ArrayList<>();
		Iterator<Agent> it = this.iterator(); 
		while(it.hasNext()) {
			Agent a = it.next(); 
			if (a instanceof Cow) {
				result.add((Cow)a);
			}
		}
		return result; 
	}
	
	
	public void setCowGreediness(int greed) {
		for (Agent a : this.getCows()) {
			if (a instanceof Cow) {
				((Cow) a).greediness = greed; 
			}
		}
	}

	@Override
	public void populate() {
		for (int i = 0; i < numCows; i++) {
			Cow cow = new Cow();
			int x = Utilities.rng.nextInt(World.SIZE);
			int y = Utilities.rng.nextInt(World.SIZE);
			cow.setPosition(x, y); 
			addAgent(cow);
		}
		
	}

}
