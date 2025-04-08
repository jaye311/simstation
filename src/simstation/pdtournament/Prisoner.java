package simstation.pdtournament;

import simstation.Agent;
import simstation.World;

public class Prisoner extends Agent {
    private Strategy myStrategy;
    private int fitness = 0;
    private boolean partnerCheated;
    private boolean cheated;
    public Prisoner(Strategy strategy){
        myStrategy = strategy;
        strategy.myPrisoner = this;
        cheated = cooperate();
    }
    @Override
    public void update() {
        partnerCheated = ((Prisoner)world.getNeighbor(this, World.SIZE)).getCheated();
        if(cheated) {
            if(!partnerCheated)
                updateFitness(3);
        }
        else{
            if(!partnerCheated)
                updateFitness(5);
            else
                updateFitness(1);
        }
        cheated = cooperate();
        //to decrease rate of fitness increase - better viewing experience - Prisoners had to think hard
        try{
            Thread.sleep(100);
        }
        catch (InterruptedException e){
            onInterrupted();
        }
    }
    public boolean cooperate() { return myStrategy.cooperate(); }
    public void updateFitness(int amt) {
        fitness += amt;
    }
    public boolean getPartnerCheated(){
        return partnerCheated;
    }
    public boolean getCheated(){return cheated;}
    public int getFitness(){
        return fitness;
    }
    public Strategy getMyStrategy(){
        return myStrategy;
    }
    //for testing
    public void setPartnerCheated(boolean cheated){
        partnerCheated = cheated;
    }
}
