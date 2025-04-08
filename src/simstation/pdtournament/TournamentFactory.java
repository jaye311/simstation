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
        newArray[ogArray.length] = "Cooperators are Green";
        newArray[ogArray.length+1] = "Cheaters are Red";
        newArray[ogArray.length+2] = "RandomCooperators are Yellow";
        newArray[ogArray.length+3] = "Tit4Tat(ers) are Dark Gray";
        return newArray;
    }
}
