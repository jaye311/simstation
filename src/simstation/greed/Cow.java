package simstation.greed;

import mvc.Utilities;
import simstation.MobileAgent;
import simstation.World;

public class Cow extends MobileAgent {
	
	
	 int energy = 100;
	 int greediness = 25; 
	 boolean isDead = false;
	 

	 
	 
	 void eatGrass(Patch patch){
		 if(patch.energy >= greediness) {
			 patch.eatMe(this, greediness);
		 } else {
			moveToNewPatch(); 
			energy -= ((Meadow) world).movePenalty; 

		}
	 
	 }
	 
	 
	 public void setPosition(int x, int y) {
		 this.xc = x; 
		 this.yc = y; 
	 }
	 
	 private void moveToNewPatch() {
		int attempts = 0; 
		int oldPatchX = xc / Patch.patchSize; 
		int oldPatchY = yc / Patch.patchSize; 
		
		while (attempts < 10) {
			int angle = Utilities.rng.nextInt(360);
			int distance = 10 + Utilities.rng.nextInt(40);
			double radians = Math.toRadians(angle);
			int dx = (int)(Math.cos(radians)* distance);
			int dy = (int)(Math.sin(radians)* distance); 
			
			int newX = xc + dx; 
			int newY = yc + dy; 
			
			newX = Math.max(0, Math.min(newX, World.SIZE - 1));
			newY = Math.max(0, Math.min(newY, World.SIZE - 1));
			
			int newPatchX = newX / Patch.patchSize; 
			int newPatchY = newY / Patch.patchSize; 
			
			if (newPatchX != oldPatchX || newPatchY != oldPatchY) {
				xc = newX;
				yc = newY; 
				return; 
			}
			
			attempts++;
		}
		
		xc = Math.max(0, Math.min(xc + Utilities.rng.nextInt(11) - 5, World.SIZE - 1));
		yc = Math.max(0, Math.min(yc + Utilities.rng.nextInt(11) - 5, World.SIZE - 1));
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
			 ((Meadow) world).changed();
			 
			 Utilities.sleep(100);
		 }
		
		 
	}
	 

}
