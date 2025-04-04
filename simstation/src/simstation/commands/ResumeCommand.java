package simstation.commands;

import mvc.Command;
import mvc.Model;
import simstation.World;

public class ResumeCommand extends Command {
    public ResumeCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {
        ((World)model).resumeAgents();
    }
}
