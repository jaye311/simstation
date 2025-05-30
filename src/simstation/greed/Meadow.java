package simstation.greed;

import simstation.Agent;
import simstation.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


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
	//make new meadow when starting
	@Override
	public synchronized void startAgents(){
		super.startAgents();
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				synchronized (patches) {
					patches[i][j] = new Patch(this);
					patches[i][j].start();
				}
			}
		}
	}
	@Override
	public synchronized void pauseAgents() {
		super.pauseAgents();
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				synchronized (patches) {
					patches[i][j].pause();
				}
			}
		}
	}
	@Override
	public synchronized void resumeAgents(){
		super.resumeAgents();
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				synchronized (patches) {
					patches[i][j].resume();
				}
			}
		}
	}
	@Override
	public synchronized void stopAgents(){
		super.stopAgents();
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				synchronized (patches) {
					patches[i][j].stop();
				}
			}
		}
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
