package midterm;

import mvc.AppFactory;
import mvc.AppPanel;

import javax.swing.*;

public class CasinoPanel extends AppPanel {
    public CasinoPanel(AppFactory factory) {
        super(factory);
        JButton b = new JButton("Roll");
        controlPanel.add(b);
        b.addActionListener(this);
    }

    public static void main(String[] args) {
        CasinoPanel p = new CasinoPanel(new CasinoFactory());
        p.display();
    }
}
