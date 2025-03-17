package mineField;

import mvc.*;


public class MoveCommand extends Command {


    public MoveCommand(Model model) {
        super(model);
    }

    public void execute() {
        MineField minefield = (MineField) model;
        //light.change();
    }

}