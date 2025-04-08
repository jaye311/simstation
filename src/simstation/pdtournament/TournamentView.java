package simstation.pdtournament;

import mvc.Model;
import simstation.Agent;
import simstation.WorldView;
import simstation.pdtournament.strategies.*;

import java.awt.*;

public class TournamentView extends WorldView {
    public TournamentView(Model model) {
        super(model);
    }
    @Override
    public void drawAgent(Agent a, Graphics gc){
        int[] coords = a.getPoint();
        Color oldColor = gc.getColor();
        if(((Prisoner)a).getMyStrategy() instanceof Cooperate)
            gc.setColor(Color.GREEN);
        if(((Prisoner)a).getMyStrategy() instanceof Cheat)
            gc.setColor(Color.RED);
        if(((Prisoner)a).getMyStrategy() instanceof Tit4Tat)
            gc.setColor(Color.DARK_GRAY);
        if(((Prisoner)a).getMyStrategy() instanceof RandomlyCooperate)
            gc.setColor(Color.YELLOW);

        gc.fillOval(coords[0], coords[1], 10, 10 );
        gc.setColor(oldColor);
    }
}
