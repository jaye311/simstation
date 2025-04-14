package simstation.pdtournament.strategies;

import simstation.pdtournament.Strategy;

public class Tit4Tat extends Strategy {
    private boolean prevCooperated;
    //if the previous partner cooperated, I cooperate, else I cheat back at the next partner
    @Override
    public boolean cooperate() {
        boolean curDecision = prevCooperated;
        prevCooperated = !myPrisoner.getPartnerCheated();
        return curDecision;
    }
}
