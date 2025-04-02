package simstation;

import mvc.Model;
import mvc.Utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class World extends Model {
    public static final int SIZE = 500;
    private int clock = 0;
    private int alive = 0;
    private ObserverAgent observer = new ObserverAgent(this);
    private List<Agent> agents= new ArrayList<>();
    public void addAgent(Agent a){
        agents.add(a);
    };
    public void startAgents(){
        populate();
        for(Agent a: agents){
            a.start();
        }
    };
    public void stopAgents(){
        for(Agent a: agents){
            a.stop();
        }
    }
    public void pauseAgents(){
        for(Agent a: agents){
            a.pause();
        }
    }
    public void resumeAgents(){
        for(Agent a: agents){
            a.resume();
        }
    }
    public abstract void populate();
    public String getStatus(){
        return null;
    }
    public void updateStatistics(){
        clock++;
        int count = 0;
        for (Agent a: agents){
            if(!a.stopped)
                count++;
        }
        alive = count;
    }
    public void changed(String name, int oldPoint, int newPoint){
        changed();
    }
    public Agent getNeighbor(Agent caller, int radius){
        int ogIndex = Utilities.rng.nextInt(agents.size());
        int index = ogIndex;
        //while steps to get to agent is greater than looking radius
        while(Math.abs(agents.get(index).xc - caller.xc) + Math.abs(agents.get(index).yc - caller.yc) > radius) {
            index++;
            if(index == ogIndex)
                return null;
            if(index == agents.size())
                index = 0;
        }
        if(Math.abs(agents.get(index).xc - caller.xc) + Math.abs(agents.get(index).yc - caller.yc) <= radius)
            return agents.get(index);
        return null;
    }


    public Iterator<Agent> iterator() {
        return agents.iterator();
    }

    public int getClock() {
        return clock;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }

    public int getAlive() {
        return alive;
    }

    public void setAlive(int alive) {
        this.alive = alive;
    }

    public ObserverAgent getObserver() {
        return observer;
    }

    public void setObserver(ObserverAgent observer) {
        this.observer = observer;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }
}
