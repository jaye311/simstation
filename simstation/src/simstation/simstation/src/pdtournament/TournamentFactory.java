package pdtournament;

import mvc.Model;
import mvc.View;
import simstation.WorldFactory;

public class TournamentFactory extends WorldFactory {
    @Override
    public Model makeModel() {
        return new TournamentSimulation();
    }
    @Override
    public String getTitle() { return "Prisoner's Dilemma Tournament";}
    @Override
    public View makeView(Model model){
        return new TournamentView(model);
    }
}
