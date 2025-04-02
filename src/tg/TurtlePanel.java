package tg;

import mvc.Publisher;
import mvc.Subscriber;
import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;

class Turtle extends Publisher implements Serializable {
    public static int WORLD_SIZE = 250;
    private List<RestPoint> path;

    public Turtle() {
        path = new ArrayList<RestPoint>();
        RestPoint location = new RestPoint(WORLD_SIZE / 2, WORLD_SIZE / 2, Color.RED, false);
        path.add(location);
    }

    public RestPoint getLocation() {
        RestPoint result = null;
        if (path.isEmpty()) {
            // probably should throw an exception here but then
            // paintComponent must catch it and do what?
            result = new RestPoint(0, 0, Color.BLACK,true);
        } else {
            result = path.get(path.size() - 1);
        }
        return result;
    }

    public void move(Heading heading, Integer steps) throws Exception {
        RestPoint dest = null; // destination
        RestPoint location = getLocation();
        switch (heading) {
            case WEST: {
                dest = new RestPoint((Math.max(0, location.getXc() - steps)), location.getYc(), location.getColor(), location.isPenUp());
                break;
            }
            case EAST: {
                dest = new RestPoint(Math.min(WORLD_SIZE, location.getXc() + steps), location.getYc(), location.getColor(), location.isPenUp());
                break;
            }
            case SOUTH: {
                dest = new RestPoint(location.getXc(), Math.min(WORLD_SIZE, location.getYc() + steps), location.getColor(), location.isPenUp());
                break;
            }
            case NORTH: {
                dest = new RestPoint(location.getXc(), Math.max(0, location.getYc() - steps)  % WORLD_SIZE, location.getColor(), location.isPenUp());
                break;
            }
            default: {
                throw new Exception("Invalid command in Turtle.move");
            }
        }
        path.add(dest);
        notifySubscribers();
    }

    public void clear() {
        path.clear();
        RestPoint location = new RestPoint(WORLD_SIZE / 2, WORLD_SIZE / 2, Color.RED, false);
        path.add(location); // start over
        notifySubscribers();
    }

    public void setPenUp(boolean penUp) {
        this.getLocation().setPenUp(penUp);
        notifySubscribers();
    }

    public boolean isPenUp() {
        return getLocation().isPenUp();
    }

    public void setColor(Color newColor) {
        getLocation().setColor(newColor);
    }

    public Color getColor() {
        return getLocation().getColor();
    }

    public Iterator<RestPoint> iterator() {
        return path.iterator();
    }
}

class RestPoint implements Serializable {
    private int xc, yc;
    private Color color;
    private boolean penUp;

    public RestPoint(int xc, int yc, Color color, boolean penUp) {
        this.xc = xc;
        this.yc = yc;
        this.color = color;
        this.penUp = penUp;
    }

    public RestPoint() {
        this(0, 0, Color.BLACK, false);
    }

    public int getXc() {
        return xc;
    }

    public int getYc() {
        return yc;
    }

    public Color getColor() {
        return color;
    }

    public boolean isPenUp() {
        return penUp;
    }

    public void setPenUp(boolean penUp) {
        this.penUp = penUp;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}



class TurtleView extends JPanel implements Subscriber {

    private Turtle turtle;

    public TurtleView(Turtle turtle) {
        this.turtle = turtle;
        turtle.subscribe(this);
        setSize(Turtle.WORLD_SIZE, Turtle.WORLD_SIZE);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
    }

    // called from file/open and file/new
    public void setTurtle(Turtle newTurtle) {
        turtle.unsubscribe(this);
        turtle = newTurtle;
        turtle.subscribe(this);
        repaint();
    }

    public void update() { repaint(); }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Color oldColor = gc.getColor();
        Iterator<RestPoint> it = turtle.iterator();
        if (it.hasNext()) {
            RestPoint p1 = it.next();
            while(it.hasNext()) {
                RestPoint p2 = it.next();
                if (!p1.isPenUp()) {
                    gc.setColor(p1.getColor());
                    gc.drawLine(p1.getXc(), p1.getYc(), p2.getXc(), p2.getYc());
                }
                p1 = p2;
            }
        }
        // draw the turtle
        gc.setColor(Color.GREEN);
        RestPoint location = turtle.getLocation();
        if (location.isPenUp()) {
            gc.drawOval(location.getXc(),location.getYc() , 10, 10);
        } else {
            gc.fillOval(location.getXc(),location.getYc(), 10, 10);
        }

        gc.setColor(oldColor);
    }
}
enum Heading{WEST, EAST, SOUTH, NORTH};


public class TurtlePanel extends JPanel implements ActionListener {

    private Turtle turtle;
    private TurtleView view;

    public TurtlePanel() {
        turtle = new Turtle();
        view = new TurtleView(turtle);
        view.setBackground((Color.WHITE));
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground((Color.cyan));

        JButton north, south, east, west, clear, pen, color;

        JPanel p = new JPanel();
        p.setOpaque(false);
        north = new JButton("North");
        north.addActionListener(this);
        p.add(north);
        controlPanel.add(p);

        p = new JPanel();
        p.setOpaque(false);
        east = new JButton("East");
        east.addActionListener(this);
        p.add(east);
        controlPanel.add(p);

        p = new JPanel();
        p.setOpaque(false);
        west = new JButton("West");
        west.addActionListener(this);
        p.add(west);
        controlPanel.add(p);

        p = new JPanel();
        p.setOpaque(false);
        south = new JButton("South");
        south.addActionListener(this);
        p.add(south);
        controlPanel.add(p);

        p = new JPanel();
        p.setOpaque(false);
        clear = new JButton("Clear");
        clear.addActionListener(this);
        p.add(clear);
        controlPanel.add(p);

        p = new JPanel();
        p.setOpaque(false);
        pen = new JButton("Pen");
        pen.addActionListener(this);
        p.add(pen);
        controlPanel.add(p);

        p = new JPanel();
        p.setOpaque(false);
        color = new JButton("Color");
        color.addActionListener(this);
        p.add(color);
        controlPanel.add(p);

        JFrame frame = new JFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setDefaultCloseOperation(3);
        frame.setTitle("Turtle Graphics");
        frame.setSize(500, 300);

        this.setLayout((new GridLayout(1, 2)));
        this.add(controlPanel);
        this.add(view);

        frame.setVisible(true);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", new String[]{"North", "South", "East", "West", "Clear", "Pen", "Color"}, this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String cmmd = e.getActionCommand();
            // using the new switch command:
            switch(cmmd) {
                case "North", "East", "South", "West" -> {
                    int steps = Integer.parseInt(Utilities.ask("How many steps?"));
                    //turtle.move(Heading.parse(cmmd), steps);
                }
                case "Clear" -> turtle.clear();
                case "Pen" -> turtle.setPenUp(!turtle.isPenUp());
                case "Color" -> {
                    Color newColor = JColorChooser.showDialog(null, "Pick a color", turtle.getColor());
                    turtle.setColor(newColor);
                }
                case "Save" -> {
                    String fName = Utilities.getFileName((String) null, false);
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                    os.writeObject(this.turtle);
                    os.close();
                }
                case "Open" -> {
                    String fName = Utilities.getFileName((String) null, true);
                    ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                    this.turtle = (Turtle) is.readObject();
                    this.view.setTurtle(turtle);
                    is.close();
                }
                case "New" -> {
                    turtle = new Turtle();
                    view.setTurtle(turtle);
                }
                case "Quit" -> System.exit(0); // normal exit
                case "About" -> Utilities.inform("Cyberdellic Designs Turtle Graphics, 2021. All rights reserved.");
                case "Help" -> Utilities.inform(
                        new String[] {
                                "North, South, East, West prompts user for number of steps, then moves turtle in the specified heading",
                                "Clear erases the turtle's path and resets its location",
                                "Pen toggles the turtle's pen up and down",
                                "Color changes the color of the turtle's pen"}
                );
                default -> throw new Exception("Unrecognized command: " + cmmd);
            }
        } catch (Exception gripe) {
            Utilities.error(gripe);
        }
    }

    public static void main(String[] args) {
        TurtlePanel app = new TurtlePanel();
    }
}



