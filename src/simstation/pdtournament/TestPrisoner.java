package simstation.pdtournament;

import simstation.pdtournament.strategies.Cheat;
import simstation.pdtournament.strategies.Cooperate;
import simstation.pdtournament.strategies.RandomlyCooperate;
import simstation.pdtournament.strategies.Tit4Tat;

public class TestPrisoner {
    //testing if strategies work
    public static void main(String[] args) {
        TournamentSimulation ts = new TournamentSimulation();
        Prisoner cheater = new Prisoner(new Cheat());
        Prisoner cooperator = new Prisoner(new Cooperate());
        Prisoner randomCooperator = new Prisoner(new RandomlyCooperate());
        Prisoner randomCooperator2 = new Prisoner(new RandomlyCooperate());
        Prisoner randomCooperator3 = new Prisoner(new RandomlyCooperate());
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
        ts.addAgent(randomCooperator, 50, 30);
        ts.addAgent(cooperator, 50, 40);
        ts.addAgent(randomCooperator2, 50, 80);
        ts.addAgent(tit4tat, 50, 90);
        ts.addAgent(randomCooperator3, 50, 130);
        ts.addAgent(cheater, 50, 140);

        System.out.println(1);
        randomCooperator.update();
        System.out.println("co");
        cooperator.update();
        System.out.println(2);
        randomCooperator2.update();
        System.out.println("t");
        tit4tat.update();
        System.out.println(3);
        randomCooperator3.update();
        System.out.println("ch");
        cheater.update();

        System.out.println(randomCooperator.getFitness());
        System.out.println(cooperator.getFitness());
        System.out.println(randomCooperator2.getFitness());
        System.out.println(tit4tat.getFitness());
        System.out.println(randomCooperator3.getFitness());
        System.out.println(cheater.getFitness());

    }
}
