package simstation.greed;

import mvc.Utilities;
import simstation.MobileAgent;

public class Cow extends MobileAgent {
	
	
	 int energy = 100;
	 int greediness = 25; 
	 boolean isDead = false;
	 

	 
	 
	 void eatGrass(Patch patch){
		 if(patch.energy >= greediness) {
			 patch.eatMe(this, greediness);
			 
		 } else {
			try {
			move(1);
			energy -= ((Meadow) world).movePenalty; 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	 
	 }
	 
	 
	 public void setPosition(int x, int y) {
		 this.xc = x; 
		 this.yc = y; 
	 }
	 
	 public void update() {
		 if (this.energy <= 0) {
			 stop(); 
			 isDead = true; 
		 } else {
			 int patchX = this.xc / Patch.patchSize; 
			 int patchY = this.yc / Patch.patchSize; 
			 Patch currentPatch = ((Meadow) world).getPatch(patchX, patchY);
			 eatGrass(currentPatch);
			 energy -= ((Meadow) world).waitPenalty;
		 }
		
		 
	}
	 

}
