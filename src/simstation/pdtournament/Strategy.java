package simstation.pdtournament;

import java.io.Serializable;

public abstract class Strategy implements Serializable {
    protected Prisoner myPrisoner;
    public abstract boolean cooperate();
}

