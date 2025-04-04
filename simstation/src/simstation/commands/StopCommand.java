package simstation.commands;

import mvc.Command;
import mvc.Model;
import simstation.World;

public class StopCommand extends Command {
    public StopCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {
        ((World)model).stopAgents();

    }
}
