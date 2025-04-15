package simstation.pdtournament.strategies;

import mvc.Utilities;
import simstation.pdtournament.Strategy;

public class RandomlyCooperate extends Strategy {
    //be 50/50 about cooperating and cheating
    @Override
    public boolean cooperate() {
        return Utilities.rng.nextInt(2) == 1;
    }
}
