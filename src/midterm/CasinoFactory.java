package midterm;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

public class CasinoFactory implements AppFactory {
    @Override
    public Model makeModel() {
        return new Casino();
    }

    @Override
    public View makeView(Model model) {
        return new CasinoView(model);
    }

    @Override
    public String[] getEditCommands() {
        return new String[]{"Roll"};
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        if(type.equalsIgnoreCase("Roll"))
            return new RollCommand(model);
        return null;
    }

    @Override
    public String getTitle() {
        return "Casino";
    }

    @Override
    public String[] getHelp() {
        return new String[] {"Click roll to roll the dice, the player \"rolls\" two dice. If the sum is 7 or 11, the player's score is incremented by 1. If the sum is 3 or 12, the player's score is set to 0."};
    }

    @Override
    public String about() {
        return "Casino 2025 Jonathan Aye";
    }
}
