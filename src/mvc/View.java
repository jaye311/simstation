package mvc;

import javax.swing.*;


public class View extends JPanel implements Subscriber{
    protected Model model;

    public View(Model m){
        model = m;
        model.subscribe(this);
    }
    public void setModel(Model m){
        model.unsubscribe(this);
        model = m;
        model.subscribe(this);
        repaint();
    }
    public void update(){
        repaint();
    }
}
