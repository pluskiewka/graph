package main.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.gui.MainFrame;
import main.gui.model.Graph;
import main.gui.model.object.Point;
import main.gui.view.frame.NewLineFrame;
import main.gui.view.object.JPoint;
import main.util.Random;

public class MainController {
	private Graph model;
	private MainFrame frame;
	
	public MainController(Graph model, MainFrame frame) {
		this.model = model;
		this.frame = frame;
		
		this.frame.addNewLineListener(new NewLineListener());
		this.frame.addNewPointListener(new NewPointListener());
	}
	
	class NewLineListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			NewLineFrame newFrame = new NewLineFrame(frame.getCanvas().getPoints());
			new NewLineController(model,newFrame,frame.getCanvas());
		}
		
	}
	
	class NewPointListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Point point = new Point();
			model.addPoint(point);
			frame.getCanvas().addPoint(new JPoint(point, Random.nextInt(600), Random.nextInt(400)));
			frame.getCanvas().repaint();
		}
		
	}
	
}
