package simstation;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;
import simstation.commands.*;

public abstract class WorldFactory implements AppFactory {
    @Override
    public abstract Model makeModel();

    @Override
    public View makeView(Model model) {
        return new WorldView(model);
    }

    @Override
    public String[] getEditCommands() {
        return new String[]{"Start", "Pause", "Resume", "Stop", "Stats"};
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        if(type.equals("Start"))
            return new StartCommand(model);
        else if(type.equals("Stop"))
            return new StopCommand(model);
        else if(type.equals("Resume"))
            return new ResumeCommand(model);
        else if(type.equals("Stats"))
            return new StatsCommand(model);
        else if(type.equals("Pause"))
            return new SuspendCommand(model);
        return null;
    }

    @Override
    public String getTitle() {
        return "Simstation";
    }

    @Override
    public String[] getHelp() {
        return new String[]{"Start starts the simulation (Agents populate the world)",
                "Pause pauses the simulation", "Resume resumes the simulation",
                "Stop stops the simulation (Agents die, simulation must be restarted)"
                , "Stats displays statistics of simulation"};
    }

    @Override
    public String about() {
        return "2025 Simstation by Johnathan Aye, Brendan Ly, Brian Brown";
    }
}
