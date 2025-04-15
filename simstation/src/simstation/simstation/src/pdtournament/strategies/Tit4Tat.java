package pdtournament.strategies;

import pdtournament.Strategy;

public class Tit4Tat extends Strategy {
    private boolean prevCooperated;

    @Override
    public boolean cooperate() {
        boolean curDecision = prevCooperated;
        prevCooperated = !myPrisoner.getPartnerCheated();
        return curDecision;
    }
}
