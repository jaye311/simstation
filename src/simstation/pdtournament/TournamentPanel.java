package simstation.pdtournament;

import simstation.WorldFactory;
import simstation.WorldPanel;

public class TournamentPanel extends WorldPanel {
    public TournamentPanel(WorldFactory factory) {
        super(factory);
    }

    public static void main(String[] args) {
        TournamentPanel panel = new TournamentPanel(new TournamentFactory());
        panel.display();
    }
}
