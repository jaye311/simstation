package simstation.plague;

import simstation.Agent;
import simstation.World;
import mvc.AppPanel;

public class PlagueSimulation extends World {
    public static int INITIAL_INFECTED = 10; // % of hosts initially infected
    public static int VIRULENCE = 50; // % chance of infection
    public static int RESISTANCE = 2; // % chance of resisting infection
    public static int POPULATION_SIZE = 50; // # of initial hosts
    public static int FATALITY_TIME = 100;
    private boolean isFatal = false;
    private int total = 0;
    private int infected = 0;
    // private int alive = 0;
    // private int clock = 0;

    public void setInitialInfectedPercentage(int percentage) {
        PlagueSimulation.INITIAL_INFECTED = percentage;
    }

    public void setInfectionProbability(int probability) {
        PlagueSimulation.VIRULENCE = probability;
    }

    public void setPopulationSize(int size) {
        PlagueSimulation.POPULATION_SIZE = size;
    }

    public void setFatalityTime(int time) {
        PlagueSimulation.FATALITY_TIME = time;
    }

    public void setFatal(boolean fatal) {
        this.isFatal = fatal;
    }

    @Override
    public void populate() {
        int infectedCount = (int)((POPULATION_SIZE * INITIAL_INFECTED) / 100.0);
        
        for (int i = 0; i < infectedCount; i++) {
            addAgent(new Host(true, VIRULENCE, FATALITY_TIME, isFatal));
        }
        
        for (int i = 0; i < POPULATION_SIZE - infectedCount; i++) {
            addAgent(new Host(false, VIRULENCE, FATALITY_TIME, isFatal));
        }
    }

    public void updateStatistics() {
      incrementClock();
      int count = 0;
      for (var it = iterator(); it.hasNext();) {
        Host host = (Host) it.next();
        total++;
        if (host.isInfected()) {
          infected++;
        }
      }
      setAlive(count);
    }

    @Override
    public String getStatus() {
        double percentage = total > 0 ? (infected * 100.0) / total : 0;
        return String.format("Population: %d \n Infected: %.1f%% \n Clock: %d", total, percentage, getClock());
    }

    public static void main(String[] args) {
        AppPanel panel = new PlaguePanel(new PlagueFactory());
        panel.display();
    }
}
