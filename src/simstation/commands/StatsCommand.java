package simstation.commands;

import mvc.Command;
import mvc.Model;
import mvc.Utilities;
import simstation.World;

public class StatsCommand extends Command {
    public StatsCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {
        ((World) model).getObserver().update();
        Utilities.inform(((World)model).getStatus());
    }
}
