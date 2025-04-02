package review;

import mvc.*;

import javax.swing.*;

public class ReactorPanel extends AppPanel{
    public ReactorPanel(AppFactory factory) {
        super(factory);
        JButton b = new JButton("incTemp");
        controlPanel.add(b);
        b.addActionListener(this);
        b = new JButton("decTemp");
        controlPanel.add(b);
        b.addActionListener(this);
    }

    public static void main(String[] args) {
        ReactorPanel n = new ReactorPanel(new ReactorFactory());
        n.display();
    }
}
class Reactor extends Model {
    private int temp = 4600;
    public static int maxTemperature = 5000;

    public void inc() throws Exception{
        temp += Utilities.rng.nextInt(100);
        if (temp > maxTemperature)
            throw new Exception("Reactor is too hot!");
    }
    public void dec() throws Exception{
        temp -= Utilities.rng.nextInt(100);
        if (temp > maxTemperature)
            throw new Exception("Reactor is too hot!");
    }
    public void changeTemp(boolean positive) throws Exception{
        if(positive)
            inc();
        else
            dec();
        changed();
    }

    public int getTemp() {
        return temp;
    }
}
class ReactorView extends View{
    private JTextField thermometer;

    public ReactorView(Model model) {
        super(model);
        thermometer = new JTextField("" + ((Reactor)model).getTemp());
        add(thermometer);
    }

    public void update() {
        thermometer.setText(("" + ((Reactor)model).getTemp()));
    }
}
class ReactorFactory implements AppFactory{

    @Override
    public Model makeModel() { return new Reactor(); }

    @Override
    public View makeView(Model m) {
        return new ReactorView(m);
    }

    @Override
    public String[] getEditCommands() {
        return new String[]{"decTemp", "incTemp"};
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        if (type.equals("incTemp"))
            return new TempCommand(model, true);
        else if(type.equals("decTemp"))
            return new TempCommand(model, false);
        return null;
    }

    @Override
    public String getTitle() { return "Reactor Control"; }

    @Override
    public String[] getHelp() {
        return new String[] {"Click dec or inc to dec or inc"};
    }

    @Override
    public String about() {
        return "Reactor 2025 Jonathan Aye";
    }
}
class TempCommand extends Command{
    private boolean positive;
    public TempCommand(Model model, boolean positive) {
        super(model);
        this.positive = positive;
    }
    public void execute() throws Exception {
        ((Reactor) model).changeTemp(positive);
        model.changed();
    }

}
