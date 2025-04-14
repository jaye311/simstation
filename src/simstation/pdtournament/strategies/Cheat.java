package simstation.pdtournament.strategies;

import simstation.pdtournament.Strategy;

public class Cheat extends Strategy {
    //always cheat
    @Override
    public boolean cooperate() {
        return false;
    }
}
