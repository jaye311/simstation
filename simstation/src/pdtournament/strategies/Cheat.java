package pdtournament.strategies;

import pdtournament.Strategy;

public class Cheat extends Strategy {

    @Override
    public boolean cooperate() {
        return false;
    }
}
