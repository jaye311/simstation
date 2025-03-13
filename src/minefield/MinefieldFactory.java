package minefield;

import mvc.*;

public class MinefieldFactory implements AppFactory {

    public Model makeModel() { return new MineField(); }

    public View makeView(Model m) {
        return new MinefieldView((MineField)m);
    }

    public String[] getEditCommands() {
            return new String[]{"N", "E", "S", "W", "NW", "NE", "SW", "SE"};
    }

    // source added 3/15 to support text fields
    public Command makeEditCommand(Model model, String type, Object source) {
        if (type == "N" || type == "E" || type == "S" || type == "W" || type == "NW" || type == "NE" || type == "SW" || type == "SE")
            return new MoveCommand(model);
        return null;
    }

    public String getTitle() { return "Mine Field"; }

    public String[] getHelp() {
        return new String[] {"Click N, E, S, W, NW, NE, SW, SE to move the minefield"};
    }

    public String about() {
        return "Minefield version 1.0. Copyright 2025 by Cyberdellic Designs";
    }

}
