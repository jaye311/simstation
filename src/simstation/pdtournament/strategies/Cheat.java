package simstation.pdtournament.strategies;

import simstation.pdtournament.Strategy;

public class Cheat extends Strategy {

    @Override
    public boolean cooperate() {
        return false;
    }
}
