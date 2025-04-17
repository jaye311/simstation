package simstation.greed;

import mvc.Utilities;
import simstation.Heading;
import simstation.MobileAgent;


public class Cow extends MobileAgent {

	 protected int energy = 100;
	 public static int greediness = 25;
	 protected Patch location;



	 //for if you want to go fast and be different
	 /*public void moveToNewPatch() {
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
		location = ((Meadow) world).getPatch(xc/Patch.patchSize, yc/Patch.patchSize);
	 }*/

	public void moveToNewPatch() {
		heading = Heading.random();
		int steps = Utilities.rng.nextInt(5)*Patch.patchSize+Patch.patchSize;
		move(steps);
		location = ((Meadow) world).getPatch(xc/Patch.patchSize, yc/Patch.patchSize);
	}
	 @Override
	 public void update()  {
		 if (this.energy > 0) {
             location.eatMe(this, greediness);
		 }
		 else {
			 stop();
		 }
	}


}
