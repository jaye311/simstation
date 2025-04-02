package midterm;

import mvc.Model;
import mvc.View;

import javax.swing.*;
import java.awt.*;

public class CasinoView extends View {
    JTextField die1Field = new JTextField("1", 5); // displays 1 in 5 columns
    JTextField die2Field = new JTextField("2", 5); // displays 2 in 5 columns
    JTextField scoreField = new JTextField("0", 5); // displays 0 in 5 columns
    JLabel die1Label = new JLabel("die1");
    JLabel die2Label = new JLabel("die2");
    JLabel scoreLabel = new JLabel("score");

    public CasinoView(Model model) {
        super(model);
        setLayout(new GridLayout(3, 2));
        JPanel p = new JPanel();
        p.add(die1Label);
        add(p);
        p = new JPanel();
        p.add(die1Field);
        add(p);
        p = new JPanel();
        p.add(die2Label);
        add(p);
        p = new JPanel();
        p.add(die2Field);
        add(p);
        p = new JPanel();
        p.add(scoreLabel);
        add(p);
        p = new JPanel();
        p.add(scoreField);
        add(p);
    }
    @Override
    public void update(){
        die1Field.setText(""+((Casino) model).getDice1());
        die2Field.setText(""+((Casino) model).getDice2());
        scoreField.setText(""+((Casino) model).getScore());
    }
}
