package simstation.greed;

import mvc.Model;
import mvc.View;
import simstation.WorldFactory;

public class GreedFactory extends WorldFactory {

	@Override
	public Model makeModel() {
		return new Meadow();
	}
	 
	@Override
	public View makeView (Model model) {
		 return new GreedView(model);
	 }

	@Override
	public String getTitle() {
		return "Greed";
	}


}
