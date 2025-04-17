package simstation.greed;

import simstation.Agent;

public class Patch extends Agent{
	
	int energy  = 100; 
	int growbackrate = 1; 
	public static int patchSize = 10;

	public Patch(Meadow m){
		super(m);
	}
	synchronized void eatMe (Cow cow, int amt)   {
		try {
			if (cow.energy <= 0) {
				cow.stop();
			}
			if (this.energy >= amt) {
				this.energy = this.energy - amt;
				cow.energy = cow.energy + amt;
			} else if (cow.energy > ((Meadow) world).getMovePenalty()) {
				cow.moveToNewPatch();
				cow.energy -= ((Meadow) world).getMovePenalty();

			} else {
				this.wait();
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
			this.energy = this.energy + growbackrate;
			if (this.energy > 100)
				this.energy = 100;
		}
        notifyAll();
	}
	

}
