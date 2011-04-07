package main.gui.controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import main.gui.model.Graph;
import main.gui.model.object.Line;
import main.gui.model.object.Point;
import main.gui.model.table.LineTableModel;
import main.gui.model.table.PointTableModel;

public class CanvasController {

	private Graph model;
	private Point point, toLine;
	private PointTableModel pointTableModel;
	private LineTableModel lineTableModel;
	@SuppressWarnings("unused")
	private Line line;
	private JPanel panel;
	
	public CanvasController(Graph model, PointTableModel pointTableModel, LineTableModel lineTableModel, JPanel panel) {
		this.model = model;
		this.panel = panel;
		this.pointTableModel = pointTableModel;
		this.lineTableModel = lineTableModel;
		
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
				
				point.setX(x);
				point.setY(y);
				/* Tylko repaintowanie, update danych w tabelach odbędzie się po
				 * wykonaniu operacji dodania punktu/krawędzi albo zakończeniu
				 * przesuwania punktu. */
				panel.repaint();
			}
		}
	}
	
	private void update() {
		model.updateAll();
		pointTableModel.fireTableDataChanged();
		lineTableModel.fireTableDataChanged();
		panel.repaint();
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
					model.addLine(point, toLine, 1);
					toLine.setColor(Color.BLUE);
					update();
					point = null;
					toLine = null;
				}
			} else if (e.getClickCount() == 2) {
				if(point != null) {
					toLine = point;
					toLine.setColor(Color.RED);
					update();
				} else {
					Point point = new Point(e.getX(), e.getY());
					model.addPoint(point);
					update();
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
		
		/**
		 * Aktualizacja danych w tabeli.
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
			update();
		}
	}
}
