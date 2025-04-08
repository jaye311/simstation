package simstation.pdtournament;

import simstation.Agent;
import simstation.World;
import simstation.pdtournament.strategies.*;

import java.util.Iterator;

public class TournamentSimulation extends World {
    private int cheatFitness;
    private int tit4tatFitness;
    private int coopFitness;
    private int randFitness;
    @Override
    public void populate() {
        for(int i = 0; i < 10; i++)
            addAgent(new Prisoner(new Cheat()));
        for(int i = 0; i < 10; i++)
            addAgent(new Prisoner(new Cooperate()));
        for(int i = 0; i < 10; i++)
            addAgent(new Prisoner(new RandomlyCooperate()));
        for(int i = 0; i < 10; i++)
            addAgent(new Prisoner(new Tit4Tat()));
    }
    @Override
    public void updateStatistics(){
        super.updateStatistics();
        int cheatSize = 0;
        int coopSize = 0;
        int randSize = 0;
        int tit4tatSize = 0;
        cheatFitness = 0;
        tit4tatFitness = 0;
        coopFitness = 0;
        randFitness = 0;
        Iterator<Agent> prisoners= iterator();
        while(prisoners.hasNext()){
            Prisoner p = ((Prisoner)prisoners.next());
            if(p.getMyStrategy() instanceof Cooperate) {
                coopFitness += p.getFitness();
                coopSize++;
            }
            else if(p.getMyStrategy() instanceof Cheat){
                cheatFitness += p.getFitness();
                cheatSize++;
            }
            else if(p.getMyStrategy() instanceof RandomlyCooperate){
                randFitness += p.getFitness();
                randSize++;
            }
            else if(p.getMyStrategy() instanceof Tit4Tat){
                tit4tatFitness += p.getFitness();
                tit4tatSize++;
            }
        }
        if(cheatSize > 0)
            cheatFitness /= cheatSize;
        if(tit4tatSize > 0)
            tit4tatFitness /= tit4tatSize;
        if(coopSize > 0)
            coopFitness /= coopSize;
        if(randSize > 0)
            randFitness /= randSize;
    }
    @Override
    public String getStatus(){
        String s = super.getStatus();
        return s+ "\n#Cheater Average Fitness: "+ cheatFitness+ "\n#Cooperator Average Fitness: "+ coopFitness +
                "\n#RandomCooperator Average Fitness: " + randFitness+ "\n#Tit4Tat(er) Average Fitness: "+ tit4tatFitness;
    }

}
