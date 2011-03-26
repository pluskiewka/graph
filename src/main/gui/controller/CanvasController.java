package main.gui.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.gui.model.Graph;
import main.gui.model.object.Line;
import main.gui.model.object.Point;
import main.gui.view.CanvasPanel;

public class CanvasController {

	private Graph model;
	private Point point, toLine;
	private CanvasPanel panel;
	
	public CanvasController(Graph model, CanvasPanel panel) {
		this.model = model;
		this.panel = panel;
		
		this.panel.addMouseListener(new MouseListener());
		this.panel.addMouseMotionListener(new MouseListener());
	}
	
	/**
	 * Przemieszczanie obiektu, a przy okazji dokonywanie przemalowania 
	 * całego canvas.
	 */
	private void movePoint(int x, int y) {
		if(point != null) {
			final int CURR_X = point.getX();
			final int CURR_Y = point.getY();
			
			if((CURR_X != x) || (CURR_Y != y)) {
				panel.repaint(0,0,panel.getWidth(),panel.getHeight());
				
				point.setX(x);
				point.setY(y);
				
				panel.repaint(0,0,panel.getWidth(),panel.getHeight());
			}
		}
	}
	
	class MouseListener extends MouseAdapter {
		/**
		 * Wołana w momencie gdy następuje wciśnięcie przycisku, gdy wskaźnik
		 * jest na powierzchni canvas. Szukamy punktu w otoczeniu miejsca 
		 * przyciśnięcia.
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			point = null;
			for(Point p : model.getPoints()) {
				if(p.getX() > e.getX()-10 && p.getX() < e.getX()+10 && p.getY() > e.getY()-10 && p.getY() < e.getY()+10) {
					point = p;
					break;
				}
			}
			if(e.getClickCount() == 1) {
				if(point != null && toLine == null)
					movePoint(e.getX(), e.getY());
				else if(point != null && toLine != null) {
					Line line = new Line(point, toLine, 1);
					model.addLine(line);
					panel.repaint();
					point = null;
					toLine = null;
				}
			} else if (e.getClickCount() == 2) {
				if(point != null)
					toLine = point;
				else {
					Point point = new Point(e.getX(), e.getY());
					model.addPoint(point);
					panel.repaint();
					point = null;
				}
			}
		}
		
		/**
		 * Wołana w momencie gdy przyciśnięta myszka jest przemieszczana po 
		 * canvas, nie zależnie od tego, czy coś trzymamy czy nie.
		 */
		@Override
		public void mouseDragged(MouseEvent e) {
			movePoint(e.getX(), e.getY());
		}
	}
}
