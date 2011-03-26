package main.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.gui.model.Graph;
import main.gui.model.object.Line;
import main.gui.view.CanvasPanel;
import main.gui.view.frame.NewLineFrame;
import main.gui.view.object.JLine;
import main.gui.view.object.JPoint;

public class NewLineController {

	private Graph model;
	private NewLineFrame frame;
	private CanvasPanel canvas;
	
	public NewLineController(Graph model, NewLineFrame frame, CanvasPanel canvas) {
		this.model = model;
		this.frame = frame;
		this.canvas = canvas;
		
		this.frame.addCancelListener(new CancelListener());
		this.frame.addOkeyListener(new OkeyListener());
	}
	
	class CancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
		}
		
	}
	
	class OkeyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JPoint left = frame.getLeftPoint(), right = frame.getRightPoint();
			int level = frame.getLevel();
			Line line = new Line(left.getPoint(), right.getPoint(), level);
			model.addLine(line);
			canvas.addLine(new JLine(line, left, right));
			canvas.repaint();
			frame.dispose();
		}
		
	}

}
