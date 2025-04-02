package life;

import mvc.*;

import java.io.Serializable;
import java.awt.*;
import javax.swing.*;


class Cell implements Serializable {
    int livingNeighbors = 0;
    boolean alive = false;
    void updateCell() {
        alive = livingNeighbors >= 1 && livingNeighbors <= 3;
    }
}

public class Grid extends Model {

    static int dim = 20;
    static int percentAlive = 25;
    Cell cells[][];

    public Grid() {
        cells = new Cell[dim][dim];
        for(int row = 0; row < dim; row++) {
            for(int col = 0; col < dim; col++) {
                cells[row][col] = new Cell();
                cells[row][col].alive = Utilities.rng.nextInt(100) < percentAlive;
            }
        }
        setNeighbors();
        updateCells();
    }

    private void setNeighbors() {
        for(int row = 0; row < dim; row++) {
            for(int col = 0; col < dim; col++) {
                int count = 0;
                for(int i = -1; i <= 1; i++) {
                    for(int j = -1; j <= 1; j++) {
                        if (i == 0 && j == 0) continue;
                        int r = (row + i) % dim;
                        int c = (col + j) % dim;
                        if (r < 0) r = dim - 1;
                        if (c < 0) c = dim - 1;
                        if (cells[r][c].alive) count++;
                    }
                }
                cells[row][col].livingNeighbors = count;
            }
        }
    }

    public void updateCells() {
        for(int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                cells[row][col].updateCell();
            }
        }
        setNeighbors();
        changed();
    }
}

class GridView extends View {
    JLabel gridPanels[][];
    public GridView(Model grid) {
        super(grid);
        Grid g = (Grid)grid;
        setLayout(new GridLayout(Grid.dim, Grid.dim));
        gridPanels = new JLabel[Grid.dim][Grid.dim];
        for(int row = 0; row < Grid.dim; row++) {
            for(int col = 0; col < Grid.dim; col++) {
                gridPanels[row][col] = new JLabel("" + g.cells[row][col].livingNeighbors);
                gridPanels[row][col].setOpaque(true);
                if (g.cells[row][col].alive) {
                    gridPanels[row][col].setBackground(Color.GREEN);
                } else {
                    gridPanels[row][col].setBackground(Color.RED);
                }
                this.add(gridPanels[row][col]);
            }
        }
    }

    public void update() {
        // update text and background color of each JLabel
        for(int row = 0; row < Grid.dim; row++) {
            for (int col = 0; col < Grid.dim; col++) {
                if (((Grid)model).cells[row][col].alive) {
                    gridPanels[row][col].setBackground(Color.GREEN);
                } else {
                    gridPanels[row][col].setBackground(Color.RED);
                }
                this.add(gridPanels[row][col]);
            }
        }
    }
}

class UpdateCommand extends Command {
    public UpdateCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {
        ((Grid)model).updateCells();
    }
}

class GridFactory implements AppFactory {

    @Override
    public Model makeModel() { return new Grid(); }

    @Override
    public View makeView(Model m) {
        return new GridView(m);
    }

    @Override
    public String[] getEditCommands() {
        return new String[]{"update"};
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        if (type.equals("update"))
            return new UpdateCommand(model);
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


class GridPanel extends AppPanel {
    public GridPanel(AppFactory factory) {
        super(factory);
        JButton b = new JButton("update");
        b.addActionListener(this);
        controlPanel.add(b);
        // add an update button to the control panel
    }

    public static void main(String[] args) {
        AppPanel p = new GridPanel(new GridFactory());
        p.display();
    }
}
