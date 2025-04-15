package simstation;

import mvc.Model;
import mvc.Utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class World extends Model {
    public static final int SIZE = 500;
    protected int clock = 0;
    protected int alive = 0;
    protected ObserverAgent observer = new ObserverAgent(this);
    protected List<Agent> agents= new ArrayList<>();
    public void addAgent(Agent a){
    	a.setWorld(this);
        //set Agent location randomly within World
        a.xc = Utilities.rng.nextInt(World.SIZE);
        a.yc = Utilities.rng.nextInt(World.SIZE);
        agents.add(a);
    }
    public synchronized void startAgents(){
        if(!agents.isEmpty()){
            stopAgents();
            agents.clear();
        }
        populate();
        for (Agent a : agents) {
            a.start();
        }
    }
    public synchronized void stopAgents(){
        for(Agent a: agents){
            a.stop();
        }
    }
    public synchronized void pauseAgents(){
        for(Agent a: agents){
            a.pause();
        }
    }
    public synchronized void resumeAgents(){
        for(Agent a: agents){
            a.resume();
        }
    }
    public abstract void populate();
    public String getStatus(){
        return "#agents: " + agents.size() + "\n#living: "+ getAlive() + "\n#clock: " + getClock() ;
    }
    //updates the amount of agents that are not stopped and increments a clock for updates
    public synchronized void updateStatistics(){
        clock++;
        int count = 0;
        for (Agent a: agents){
            if(!a.stopped)
                count++;
        }
        alive = count;
    }
    public synchronized Agent getNeighbor(Agent caller, int radius){
        //gets a random Agent from the List to start iterating from
        int ogIndex = Utilities.rng.nextInt(agents.size());
        int index = ogIndex;
        do{
            Agent neighbor = agents.get(index);
            //while steps to get to neighbor is greater than radius, or the neighbor is the caller (You are not your neighbor)
            if(neighbor != caller && caller.getDistance(neighbor) <= radius)
                return neighbor;
            index++;
            //make sure to iterate through the whole list for a potential neighbor within radius
            if(index == agents.size())
                index = 0;
        }
        while(index != ogIndex);
        //no close enough neighbor
        return null;
    }

    public Iterator<Agent> iterator() {
        return agents.iterator();
    }

    public int getClock() {
        return clock;
    }

    public int getAlive() {
        return alive;
    }

    public ObserverAgent getObserver() {
        return observer;
    }
}
