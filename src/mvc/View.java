package mvc;
import javax.swing.*;


public class View extends JPanel implements Subscriber{
    protected Model model;

    public View(Model model){
        super();
        this.model = model;
        this.model.subscribe(this);
    }

    public void setModel(Model newModel){
        if (model != null)
            model.unsubscribe(this);
        this.model = newModel;
        if (newModel != null) {
            model.subscribe(this);
            update();
        }
    }

    @Override
    public void update(){
        repaint();
    }
}