package simstation.greed;

import simstation.Agent;
import simstation.World;

import java.util.*;


public class Meadow extends World{
	
	protected int waitPenalty = 5;
	protected int movePenalty = 10;
	protected int numCows = 50;
	protected int dim = World.SIZE/Patch.patchSize;
	
	private Patch[][] patches;
	
	public Meadow() {
		agents.clear();
		patches = new Patch[dim][dim];
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				patches[i][j] = new Patch(this);
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

	@Override
	public void addAgent(Agent a){
		super.addAgent(a);
		int[] point = a.getPoint();
		((Cow)a).location = getPatch(point[0]/Patch.patchSize, point[1]/Patch.patchSize);
	}
	//200, numCows
	@Override
	public void populate() {
		for (int i = 0; i < numCows; i++) {
			Cow c = new Cow();
			addAgent(c);
			int[] point = c.getPoint();
			c.location = getPatch(point[0]/Patch.patchSize, point[1]/Patch.patchSize );
		}
	}
	//when starting set path energy to full
	@Override
	public synchronized void startAgents(){
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				patches[i][j].energy = 100;
				patches[i][j].start();
			}
		}
		super.startAgents();
	}

	//updates the amount of agents that are not stopped and increments a clock for updates
	@Override
	public synchronized void updateStatistics(){
		incrementClock();
		int count = 0;
		for (Agent a: agents){
			if(a instanceof Cow && !a.isStopped())
				count++;
		}
		alive = count;
	}

	public int getWaitPenalty(){
		return waitPenalty;
	}

	public int getMovePenalty(){
		return movePenalty;
	}

	public void setMovePenalty(int move){
		movePenalty = move;
	}

}
