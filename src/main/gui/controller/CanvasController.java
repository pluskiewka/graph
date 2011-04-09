package main.gui.controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import main.gui.model.Graph;
import main.gui.model.object.Point;
import main.gui.model.table.LineTableModel;
import main.gui.model.table.PointTableModel;

public class CanvasController {

	private Graph model;
	private Point point, toLine;
	private PointTableModel pointTableModel;
	private LineTableModel lineTableModel;
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
	
	private void updateTableData() {
		pointTableModel.fireTableDataChanged();
		lineTableModel.fireTableDataChanged();
	}
	
	private void repaint() {
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
			/* Looking for vertex */
			for(Point p : model.getPoints()) {
				if(p.getX() > e.getX()-10 
						&& p.getX() < e.getX()+10 
						&& p.getY() > e.getY()-10 
						&& p.getY() < e.getY()+10) {
					point = p;
					/* Point founded */
					break;
				}
			}
			if(e.getClickCount() == 1) {
				if(point != null && toLine == null)
					/* Dragging point */
					movePoint(e.getX()-5, e.getY()-5);
				else if(point != null && toLine != null) {
					/* Selecting second point of line, vertex of edge */
					model.addLine(point, toLine, 0);
					toLine.setColor(Color.BLACK);
					/* Updateing table data view */
					updateTableData();
					repaint();
					/* Restore default value */
					point = null;
					toLine = null;
				}
			} else if (e.getClickCount() == 2) {
				if(point != null) {
					/* We need to select next point/vertex to make line/edge */
					toLine = point;
					/* Setting color of active point/vertex */
					toLine.setColor(Color.RED);
					repaint();
				} else {
					/* Create new vertex */
					int next = Graph.getAndSetNext();
					model.addPoint(next, e.getX()-5, e.getY()-5);
					/* Update table data view */
					updateTableData();
					repaint();
					/* Restore default value */
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
			movePoint(e.getX()-5, e.getY()-5);
		}
		
		/**
		 * Aktualizacja danych w tabeli.
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
			updateTableData();
		}
	}
}
