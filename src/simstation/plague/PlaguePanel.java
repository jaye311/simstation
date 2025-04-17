package simstation.plague;

import simstation.WorldPanel;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class PlaguePanel extends WorldPanel {
    private JSlider initialInfectedSlider;
    private JSlider infectionProbabilitySlider;
    private JSlider populationSizeSlider;
    private JSlider fatalityTimeSlider;
    private JToggleButton fatalityToggle;

    public PlaguePanel(PlagueFactory factory) {
        super(factory);
        
        JPanel slidersPanel = new JPanel();
        slidersPanel.setLayout(new GridLayout(9, 1, 5, 5));
        slidersPanel.setBackground(Color.PINK);

        JLabel infectedLabel = new JLabel("Initial % Infected:", SwingConstants.CENTER);
        initialInfectedSlider = new JSlider(0, 100, 10);
        initialInfectedSlider.setPreferredSize(new Dimension(300, 40));
        initialInfectedSlider.setMaximumSize(new Dimension(300, 40));
        initialInfectedSlider.setOpaque(true);
        initialInfectedSlider.setSize(200, 50);
        initialInfectedSlider.setMajorTickSpacing(10);
        initialInfectedSlider.setPaintTicks(true);
        initialInfectedSlider.setPaintLabels(true);

        JLabel probabilityLabel = new JLabel("Infection Probability:", SwingConstants.CENTER);
        infectionProbabilitySlider = new JSlider(0, 100, 50);
        infectionProbabilitySlider.setPreferredSize(new Dimension(300, 40));
        infectionProbabilitySlider.setMaximumSize(new Dimension(300, 40));
        infectionProbabilitySlider.setOpaque(true);
        infectionProbabilitySlider.setMajorTickSpacing(10);
        infectionProbabilitySlider.setPaintTicks(true);
        infectionProbabilitySlider.setPaintLabels(true);

        JLabel populationLabel = new JLabel("Initial Population Size:", SwingConstants.CENTER);
        populationSizeSlider = new JSlider(0, 200, 50);
        populationSizeSlider.setPreferredSize(new Dimension(300, 40));
        populationSizeSlider.setMaximumSize(new Dimension(300, 40));
        populationSizeSlider.setOpaque(true);
        populationSizeSlider.setMajorTickSpacing(20);
        populationSizeSlider.setPaintTicks(true);
        populationSizeSlider.setPaintLabels(true);

        JLabel fatalityLabel = new JLabel("Fatality/Recovery Time:", SwingConstants.CENTER);
        fatalityTimeSlider = new JSlider(0, 500, 100);
        fatalityTimeSlider.setPreferredSize(new Dimension(300, 40));
        fatalityTimeSlider.setMaximumSize(new Dimension(300, 40));
        fatalityTimeSlider.setOpaque(true);
        fatalityTimeSlider.setMajorTickSpacing(50);
        fatalityTimeSlider.setPaintTicks(true);
        fatalityTimeSlider.setPaintLabels(true);

        JPanel toggleWrapper = new JPanel();
        toggleWrapper.setOpaque(false);
        toggleWrapper.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        toggleWrapper.setPreferredSize(new Dimension(100, 30));
        fatalityToggle = new JToggleButton("Not Fatal");
        fatalityToggle.setPreferredSize(new Dimension(100, 30));
        fatalityToggle.setMaximumSize(new Dimension(100, 30));
        fatalityToggle.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                fatalityToggle.setText(fatalityToggle.isSelected() ? "Fatal" : "Not Fatal");
            }
        });
        toggleWrapper.add(fatalityToggle);

        slidersPanel.add(infectedLabel);
        slidersPanel.add(initialInfectedSlider);
        slidersPanel.add(probabilityLabel);
        slidersPanel.add(infectionProbabilitySlider);
        slidersPanel.add(populationLabel);
        slidersPanel.add(populationSizeSlider);
        slidersPanel.add(fatalityLabel);
        slidersPanel.add(fatalityTimeSlider);
        slidersPanel.add(toggleWrapper);
        
        controlPanel.add(slidersPanel, BorderLayout.CENTER);
        
        ChangeListener parameterChangeListener = e -> updateSimulationParameters();
        
        initialInfectedSlider.addChangeListener(parameterChangeListener);
        infectionProbabilitySlider.addChangeListener(parameterChangeListener);
        populationSizeSlider.addChangeListener(parameterChangeListener);
        fatalityTimeSlider.addChangeListener(parameterChangeListener);
        fatalityToggle.addChangeListener(parameterChangeListener);
    }
    
    private void updateSimulationParameters() {
        if (model instanceof PlagueSimulation) {
            PlagueSimulation simulation = (PlagueSimulation) model;
            simulation.setInitialInfectedPercentage(initialInfectedSlider.getValue());
            simulation.setInfectionProbability(infectionProbabilitySlider.getValue());
            simulation.setPopulationSize(populationSizeSlider.getValue());
            simulation.setFatalityTime(fatalityTimeSlider.getValue());
            simulation.setFatal(fatalityToggle.isSelected());
        }
    }
}