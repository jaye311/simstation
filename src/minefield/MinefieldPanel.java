package mineField;

import java.awt.*;
import javax.swing.*;

import mvc.*;



public class MinefieldPanel extends AppPanel {
    private JButton N, E, S, W, NW, NE, SW, SE;

    /*public void buttonLayout(){
        JPanel p;

        //FlowLayout provides buttons with horizontal spacing
        p = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        p.add(NW);
        p.add(N);
        p.add(NE);
        controlPanel.add(p);

        p = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 6));
        p.add(W);
        p.add(new JLabel()); // Spacing placeholder (blank space)
        p.add(E);
        controlPanel.add(p);

        p = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 6));
        p.add(SW);
        p.add(S);
        p.add(SE);
        controlPanel.add(p);
    }*/

    public MinefieldPanel(AppFactory factory) {
        super(factory);

//        // Creates and adds the MinefieldView
//        view = new MinefieldView(((MineField) model));
//        model.subscribe(view); // Allows the view to update according to changes
//        this.add(view);

        N = new JButton("N");
        E = new JButton("E");
        S = new JButton("S");
        W = new JButton("W");
        NW = new JButton("NW");
        NE = new JButton("NE");
        SW = new JButton("SW");
        SE = new JButton("SE");
/*
        controlPanel.setLayout(new GridLayout(3, 3));
        buttonLayout();

        N.addActionListener(this);

        E.addActionListener(this);

        S.addActionListener(this);

        W.addActionListener(this);

        NW.addActionListener(this);

        NE.addActionListener(this);

        SW.addActionListener(this);

        SE.addActionListener(this);
       */
        //layout shown on Assignment
        controlPanel.setLayout(new GridLayout(4, 2));
        JPanel p = new JPanel();

        NW = new JButton("NW");
        NW.addActionListener(this);
        p.add(NW);
        controlPanel.add(p);
        p = new JPanel();

        N = new JButton("N");
        N.addActionListener(this);
        p.add(N);
        controlPanel.add(p);
        p = new JPanel();

        NE = new JButton("NE");
        NE.addActionListener(this);
        p.add(NE);
        controlPanel.add(p);
        p = new JPanel();

        W = new JButton("W");
        W.addActionListener(this);
        p.add(W);
        controlPanel.add(p);
        p = new JPanel();

        E = new JButton("E");
        E.addActionListener(this);
        p.add(E);
        controlPanel.add(p);
        p = new JPanel();

        SW = new JButton("SW");
        SW.addActionListener(this);
        p.add(SW);
        controlPanel.add(p);
        p = new JPanel();

        S = new JButton("S");
        S.addActionListener(this);
        p.add(S);
        controlPanel.add(p);
        p = new JPanel();

        SE = new JButton("SE");
        SE.addActionListener(this);
        p.add(SE);
        controlPanel.add(p);

    }

    public static void main(String[] args) {
        AppFactory factory = new MinefieldFactory();
        AppPanel panel = new mineField.MinefieldPanel(factory);
        panel.display();
    }

}

