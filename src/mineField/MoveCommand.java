package mineField;

import mvc.*;


public class MoveCommand extends Command {
    private String direction;

    public MoveCommand(Model model, String direction) {
        super(model);
        this.direction = direction;
    }
    public void execute ()  throws MineFieldException{
        ((MineField) model).move(direction);
        model.changed();
    }

}
