package simstation.greed;

import simstation.WorldPanel;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class GreedPanel extends WorldPanel {
	
	private JSlider grassSlider, greedSlider, moveSlider;
	
	public GreedPanel(GreedFactory factory) {
		super(factory); 
		
		grassSlider = new JSlider(0, 10, 1);
		greedSlider = new JSlider(0, 100, 25); 
		moveSlider = new JSlider(0, 50, 10); 
		
		grassSlider.setMajorTickSpacing(2);
		grassSlider.setPaintTicks(true);
		grassSlider.setPaintLabels(true);
		
		greedSlider.setMajorTickSpacing(25);
		greedSlider.setPaintTicks(true);
		greedSlider.setPaintLabels(true);
		
		moveSlider.setMajorTickSpacing(10);
		moveSlider.setPaintTicks(true);
		moveSlider.setPaintLabels(true);
		
		JPanel sliderPanel = new JPanel(new GridLayout(3, 2));
		sliderPanel.add(new JLabel("Patch Grow Rate:"));
		sliderPanel.add(grassSlider);
		sliderPanel.add(new JLabel("Greediness:"));
		sliderPanel.add(greedSlider);
		sliderPanel.add(new JLabel("Movement Penalty"));
		sliderPanel.add(moveSlider);
		
		controlPanel.add(sliderPanel, BorderLayout.SOUTH);


		ChangeListener parameterChangeListener = e -> updateSimulationParameters();


		grassSlider.addChangeListener(parameterChangeListener);
		greedSlider.addChangeListener(parameterChangeListener);
		moveSlider.addChangeListener(parameterChangeListener);
	}
	private void updateSimulationParameters() {
		if (model instanceof Meadow) {
			((Meadow) model).setGrowthRate(grassSlider.getValue());
			Cow.greediness = (greedSlider.getValue());
			((Meadow)model).setMovePenalty(moveSlider.getValue());
		}
	}

	public static void main(String[] args) {
		GreedPanel panel = new GreedPanel(new GreedFactory());
		panel.display();
	}
	


}
