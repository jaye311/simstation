package simstation.greed;

import simstation.Agent;

public class Patch extends Agent{
	
	int energy  = 100; 
	public static int growBackRate = 1;
	public static int patchSize = 10;

	public Patch(Meadow m){
		super(m);
	}

	synchronized void eatMe (Cow cow, int amt)   {
		try {
			//eat grass
			if (this.energy >= amt) {
				this.energy = this.energy - amt;
				cow.energy = cow.energy + amt;
			}
			//move away
			else if (cow.energy > ((Meadow) world).getMovePenalty()) {
				cow.moveToNewPatch();
				cow.energy -= ((Meadow) world).getMovePenalty();
			//wait to eat
			} else {
				wait();
                cow.energy -= ((Meadow) world).getWaitPenalty();
			}
		}
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
    }


	@Override
	public synchronized void update()  {
		if (this.energy < 100) {
			this.energy = this.energy + growBackRate;
			if (this.energy > 100)
				this.energy = 100;
		}
        notifyAll();
	}


}
