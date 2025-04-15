package simstation.greed;

import simstation.WorldPanel;
import mvc.AppFactory; 

import javax.swing.*; 
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
		
	}
	
	
	@Override
	public void start() {
		
		Meadow meadow = (Meadow) model; 
		
		int growthRate = grassSlider.getValue(); 
		int cowGreed = greedSlider.getValue(); 
		int movePenalty = moveSlider.getValue(); 
		
		meadow.setGrowthRate(growthRate);
		meadow.setCowGreediness(cowGreed);
		meadow.movePenalty = movePenalty; 
		
		super.start();
	}

}
