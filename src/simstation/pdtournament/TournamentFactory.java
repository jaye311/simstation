package simstation.pdtournament;

import mvc.Model;
import mvc.View;
import simstation.WorldFactory;

import java.util.Arrays;

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
    @Override
    public String[] getHelp() {
        String[] ogArray = super.getHelp();
        String[] newArray = Arrays.copyOf(ogArray, ogArray.length + 4);
        newArray[ogArray.length] = "Cooperators are Green, they cooperate";
        newArray[ogArray.length+1] = "Cheaters are Red, they cheat";
        newArray[ogArray.length+2] = "RandomCooperators are Yellow, they randomly cheat or cooperate";
        newArray[ogArray.length+3] = "Tit4Tat(ers) are Dark Gray, they payback the next partner from the previous";
        return newArray;
    }
}
