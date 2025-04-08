package simstation.pdtournament;

import simstation.Agent;
import simstation.World;

public class Prisoner extends Agent {
    private Strategy myStrategy;
    public Prisoner(Strategy strategy){
        myStrategy = strategy;
        strategy.myPrisoner = this;
    }
    @Override
    public void update() {
        boolean curStrategy = cooperate();
        partnerCheated = ((Prisoner)world.getNeighbor(this, World.SIZE)).getCooperation();
        if(curStrategy) {
            if(!partnerCheated)
                updateFitness(3);
        }
        else{
            if(!partnerCheated)
                updateFitness(5);
            else
                updateFitness(1);
        }
    }
    private int fitness = 0;
    private boolean partnerCheated = false;
    public boolean cooperate() { return myStrategy.cooperate(); }
    public void updateFitness(int amt){
        fitness += amt;
    }
    public boolean getPartnerCheated(){
        return partnerCheated;
    }
    //for testing
    public void setPartnerCheated(boolean cheated){
        partnerCheated = cheated;
    }
    public boolean getCooperation(){
        return cooperate();
    }
    public int getFitness(){
        return fitness;
    }
    public Strategy getMyStrategy(){
        return myStrategy;
    }

}
