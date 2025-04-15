package simstation.pdtournament;

import mvc.Utilities;
import simstation.Heading;
import simstation.MobileAgent;

public class Prisoner extends MobileAgent {
    private Strategy myStrategy;
    private int fitness = 0;
    private boolean partnerCheated;
    private boolean cheated;
    public Prisoner(Strategy strategy){
        myStrategy = strategy;
        strategy.myPrisoner = this;
        //I cheat if I do not cooperate
        cheated = !cooperate();
        partnerCheated = false;
    }

    @Override
    public void update() {
        //moves in random direction a random amount
        turn(Heading.random());
        move(Utilities.rng.nextInt(20)+1);
        //tries to find neighbor in radius to be in a dilemma with as partners
        Prisoner partner = (Prisoner)world.getNeighbor(this, 20);
        if(partner != null){
            //find a partner and see if they cheated (did not cooperate)
            partnerCheated = partner.getCheated();
            if(!cheated) {
                if(!partnerCheated) {
                    //if we both cooperate
                    updateFitness(3);
                    partner.updateFitness(3);
                }
                else
                    //they cheated me of my points
                    partner.updateFitness(5);
            }
            else{
                if(!partnerCheated) {
                    //I cheated and got 5 when the partner tried to cooperate
                    updateFitness(5);
                }
                else
                    //We both cheated (Well at least I didn't get 0)
                    updateFitness(1);
            }
            cheated = !cooperate();
        }
    }
    //cooperate according to my strategy
    public boolean cooperate() { return myStrategy.cooperate(); }
    //add amt to fitness
    public synchronized void updateFitness(int amt) {
        fitness += amt;
    }
    public boolean getPartnerCheated(){
        return partnerCheated;
    }
    public int getFitness(){
        return fitness;
    }
    public Strategy getMyStrategy(){
        return myStrategy;
    }
    public void setPartnerCheated(boolean b){
        partnerCheated = b;
    }
    public boolean getCheated(){return cheated;}
}
