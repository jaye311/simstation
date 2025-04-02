package simstation.commands;

import mvc.Command;
import mvc.Model;
import simstation.World;

public class StartCommand extends Command {
    public StartCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {
        ((World)model).startAgents();
    }
}
