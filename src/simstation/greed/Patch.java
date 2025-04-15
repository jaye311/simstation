package simstation.greed;

import simstation.Agent;

public class Patch extends Agent{
	
	int energy  = 100; 
	int growbackrate = 1; 
	public static int patchSize = 10; 
	
	
	void eatMe (Cow cow, int amt) {
		this.energy = this.energy - amt; 
		cow.energy = cow.energy + amt;
	}


	@Override
	public void update()  {
		if (this.energy < 100) {
		this.energy = this.energy + growbackrate; 
		}
	}
	

}
