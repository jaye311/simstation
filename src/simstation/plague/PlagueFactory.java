package simstation.plague;

import mvc.Model;
import mvc.View;
import simstation.WorldFactory;

public class PlagueFactory extends WorldFactory {
    @Override
    public Model makeModel() {
        return new PlagueSimulation();
    }

    @Override
    public View makeView(Model model) {
        return new PlagueView(model);
    }

    @Override
    public String getTitle() { 
        return "Plague";
    }

    @Override
    public String[] getHelp() {
        return new String[]{"Start begins the Plague simulation (Hosts populate the world)",
                            "Pause pauses the simulation", 
                            "Resume resumes the simulation",
                            "Stop stops the simulation (Hosts die, simulation must be restarted)",
                            "Stats displays statistics of simulation",
                            "Initial % Infected Slider: % of hosts infected when starting",
                            "Infection Probability Slider: Chance that a healthy host becomes infected when contacting an infected",
                            "Initial Population Size Slider: # of hosts in the world when starting",
                            "Fatality / Recovery Time Slider: How long it takes for an infected host to recover / die",
                            "Not Fatal / Fatal button: Toggle the plague to be fatal or non-fatal"
                          };
    }
}
