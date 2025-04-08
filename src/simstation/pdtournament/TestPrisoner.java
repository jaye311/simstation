package simstation.pdtournament;

import simstation.pdtournament.strategies.*;

public class TestPrisoner {
    //testing if strategies work
    public static void main(String[] args) {
        Prisoner cheater = new Prisoner(new Cheat());
        Prisoner cooperator = new Prisoner(new Cooperate());
        Prisoner randomCooperator = new Prisoner(new RandomlyCooperate());
        Prisoner tit4tat = new Prisoner(new Tit4Tat());
        System.out.println(cheater.cooperate());
        System.out.println(cooperator.cooperate());
        System.out.println(tit4tat.cooperate());
        System.out.println(randomCooperator.cooperate());
        System.out.println(cheater.cooperate());
        System.out.println(cooperator.cooperate());
        tit4tat.setPartnerCheated(false);
        System.out.println(tit4tat.cooperate());
        System.out.println(randomCooperator.cooperate());
    }
}
