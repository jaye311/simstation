package mvc;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

// AppPanel is the MVC controller
public class AppPanel extends JPanel implements Subscriber, ActionListener {

    protected Model model;
    protected AppFactory factory;
    protected View view;
    protected JPanel controlPanel;
    protected JFrame frame;
    List<String> history = new ArrayList<String>();
    public static int FRAME_WIDTH = 1000;
    public static int FRAME_HEIGHT = 500;

    public AppPanel(AppFactory factory) {

        // initialize fields here
        this.factory = factory;
        model = factory.makeModel();
        model.subscribe(this);
        view = factory.makeView(model);
        controlPanel = new JPanel();
        controlPanel.setBackground(Color.PINK);
        setLayout((new GridLayout(1, 2)));
        add(controlPanel);
        add(view);
        view.setBackground(Color.GRAY);
        frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(createMenuBar());
        frame.setTitle(factory.getTitle());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    public void display() { frame.setVisible(true); }

    public void update() {  /* override in extensions if needed */
    }

    public Model getModel() { return model; }

    // called by file/open and file/new
    public void setModel(Model newModel) {
        this.model.unsubscribe(this);
        this.model = newModel;
        this.model.subscribe(this);
        // view must also unsubscribe then resubscribe:
        view.setModel(this.model);
        model.changed();
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        // add file, edit, and help menus
        JMenu fileMenu =
                Utilities.makeMenu("File", new String[] {"New",  "Save", "SaveAs", "Open", "Quit"}, this);
        result.add(fileMenu);

        JMenu editMenu =
                Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        result.add(editMenu);

        JMenu helpMenu =
                Utilities.makeMenu("Help", new String[] {"About", "Help", "History"}, this);
        result.add(helpMenu);

        return result;
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String cmmd = ae.getActionCommand();

            if (cmmd.equals("Save")) {
                Utilities.save(model, false);
            } else if (cmmd.equals("SaveAs")) {
                Utilities.save(model, true);
            } else if (cmmd.equals("Open")) {
                Model newModel = Utilities.open(model);
                if (newModel != null) {
                    setModel(newModel);
                    history.clear();
                }
            } else if (cmmd.equals("New")) {
                Utilities.saveChanges(model);
                setModel(factory.makeModel());
                history.clear();
                // needed cuz setModel sets to true:
                model.setUnsavedChanges(false);
            } else if (cmmd.equals("Quit")) {
                Utilities.saveChanges(model);
                System.exit(0);
            } else if (cmmd.equals("About")) {
                Utilities.inform(factory.about());
            } else if (cmmd.equals("Help")) {
                Utilities.inform(factory.getHelp());
            }
            else if (cmmd.equals("History")) {
                Utilities.inform(history.toArray(new String[history.size()]));
            }
            else { // must be from Edit menu
                Command command = factory.makeEditCommand(model, cmmd, ae.getSource());
                command.execute();
                history.add(cmmd);
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    protected void handleException(Exception e) {
        Utilities.error(e);
    }
}