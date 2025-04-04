package simstation.commands;

import mvc.Command;
import mvc.Model;
import simstation.World;

public class SuspendCommand extends Command {
    public SuspendCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {
        ((World)model).pauseAgents();
    }
}
