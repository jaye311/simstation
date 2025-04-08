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
    	a.world = this;
        a.xc = Utilities.rng.nextInt(World.SIZE);
        a.yc = Utilities.rng.nextInt(World.SIZE);
        agents.add(a);
    }
    public void startAgents(){
        if(!agents.isEmpty())
            agents.clear();
        populate();
        for(Agent a: agents){
            a.start();
        }
    }
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
        return "#agents: " + agents.size() + "\n#living: "+ getAlive() + "\n#clock: " + getClock() ;
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
    public void changed(String name, int[] oldPoint, int[] newPoint){
        Agent movedAgent = null;
        for(Agent a: agents){
            if(a.agentName.equals(name))
                movedAgent = a;
        }
        if (null == movedAgent) {
            return;
        }
        movedAgent.xc = newPoint[0];
        movedAgent.yc = newPoint[1];
    }
    public Agent getNeighbor(Agent caller, int radius){
        //gets a random Agent from the List to start iterating from
        int ogIndex = Utilities.rng.nextInt(agents.size());
        int index = ogIndex;
        Agent neighbor = agents.get(index);
        //while steps to get to neighbor is greater than radius, or the neighbor is the caller (You are not your neighbor)
        while(Math.abs(neighbor.xc - caller.xc) + Math.abs(neighbor.yc - caller.yc) > radius || neighbor == caller) {
            neighbor = agents.get(index++);
            //no close enough neighbor
            if(index == ogIndex)
                return null;
            //make sure to iterate through the whole list for a potential neighbor within radius
            if(index == agents.size())
                index = 0;
        }
        return neighbor;
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
