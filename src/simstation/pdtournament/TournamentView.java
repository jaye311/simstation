package simstation.pdtournament;

import mvc.Model;
import simstation.Agent;
import simstation.WorldView;
import simstation.pdtournament.strategies.Cheat;
import simstation.pdtournament.strategies.Cooperate;
import simstation.pdtournament.strategies.RandomlyCooperate;
import simstation.pdtournament.strategies.Tit4Tat;

import java.awt.*;

public class TournamentView extends WorldView {
    public TournamentView(Model model) {
        super(model);
    }
    @Override
    public void drawAgent(Agent a, Graphics gc){
        super.drawAgent(a, gc);
        int[] coords = a.getPoint();
        Color oldColor = gc.getColor();
        //display individual Prisoners' fitness scores
        gc.setColor(Color.WHITE);
        gc.drawString((String.valueOf(((Prisoner) a).getFitness())), coords[0], coords[1]);
        //Cooperators
        if(((Prisoner)a).getMyStrategy() instanceof Cooperate)
            gc.setColor(Color.GREEN);
        //Cheaters
        if(((Prisoner)a).getMyStrategy() instanceof Cheat)
            gc.setColor(Color.RED);
        //Tit4Tat(ers)
        if(((Prisoner)a).getMyStrategy() instanceof Tit4Tat)
            gc.setColor(Color.DARK_GRAY);
        //RandomCooperators
        if(((Prisoner)a).getMyStrategy() instanceof RandomlyCooperate)
            gc.setColor(Color.YELLOW);
        gc.fillOval(coords[0], coords[1], 10, 10 );
        gc.setColor(oldColor);
    }
}
