package simstation.pdtournament.strategies;

import simstation.pdtournament.Strategy;

public class Cooperate extends Strategy {
    //always cooperate
    @Override
    public boolean cooperate() {
        return true;
    }
}
