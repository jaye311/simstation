package pdtournament.strategies;

import mvc.Utilities;

import pdtournament.Strategy;


public class RandomlyCooperate extends Strategy {

    @Override
    public boolean cooperate() {
        return Utilities.rng.nextInt(2) == 1;
    }
}
