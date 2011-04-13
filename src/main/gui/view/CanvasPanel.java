package main.gui.view;

import java.awt.Graphics;
import javax.swing.JPanel;

import main.model.Graph;
import main.model.object.Line;
import main.model.object.Point;

/**
 * Canvas czyli ten panel, gdzie to umieszczane są punkty oraz krawędzie i zachodzi
 * możliwość poruszania ich przy pomocy techniki drag and drop.
 */
public class CanvasPanel extends JPanel {
	private static final long serialVersionUID = -5289341181117821127L;
	private Graph model;
	
	public CanvasPanel(Graph model) {
		this.model = model;
	}
	
	/**
	 * Przemalowanie canvas. wołana automatycznie.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Point point : model.getPoints())
			point.paint(g);
		
		for(Line line : model.getLines())
			line.paint(g);
	}

}
