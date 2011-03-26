package main.gui.view;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import main.gui.view.object.JLine;
import main.gui.view.object.JPoint;

/**
 * Canvas czyli ten panel, gdzie to umieszczane są punkty oraz krawędzie i zachodzi
 * możliwość poruszania ich przy pomocy techniki drag and drop.
 */
public class CanvasPanel extends JPanel {
	private static final long serialVersionUID = -5289341181117821127L;
	
	/* Pseudo-model graficzny grafu */
	private Set<JPoint> points;
	private Set<JLine> lines;
	
	/* Domyślnie NULL, ale gdy wskażemy jakiś przycisk przyjmuje referencje na niego. */
	private JPoint point;
	
	public CanvasPanel() {
		this.points = new HashSet<JPoint>();
		this.lines = new HashSet<JLine>();
		
		this.addMouseListener(new MouseAdapter() {
			
			/**
			 * Wołana w momencie gdy następuje wciśnięcie przycisku, gdy wskaźnik
			 * jest na powierzchni canvas. Szukamy punktu w otoczeniu miejsca przyciśnięcia.
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				for(JPoint p : points) {
					if(p.getX() > e.getX()-10 && p.getX() < e.getX()+10 && p.getY() > e.getY()-10 && p.getY() < e.getY()+10) {
						point = p;
						break;
					}
				}
				System.err.println(point);
				movePoint(e.getX(), e.getY());
			}
			
		});
		
		this.addMouseMotionListener(new MouseAdapter() {
			
			/**
			 * Wołana w momencie gdy przyciśnięta myszka jest przemieszczana po canvas,
			 * nie zależnie od tego, czy coś trzymamy czy nie.
			 */
			@Override
			public void mouseDragged(MouseEvent e) {
				movePoint(e.getX(), e.getY());
			}
			
		});
	}
	
	/**
	 * Przemieszczanie obiektu, a przy okazji dokonywanie przemalowania całego canvas.
	 */
	private void movePoint(int x, int y) {
		if(point != null) {
			final int CURR_X = point.getX();
			final int CURR_Y = point.getY();
			
			if((CURR_X != x) || (CURR_Y != y)) {
				repaint(0,0,this.getWidth(),this.getHeight());
				
				point.setX(x);
				point.setY(y);
				
				repaint(0,0,this.getWidth(),this.getHeight());
			}
		}
	}
	
	public void addPoint(JPoint point) {
		points.add(point);
	}
	
	public void addLine(JLine line) {
		lines.add(line);
	}
	
	public Collection<JPoint> getPoints() {
		return points;
	}
	
	public Collection<JLine> getLines() {
		return lines;
	}
	
	/**
	 * Przemalowanie canvas. wołana automatycznie.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(JPoint point : points)
			point.paint(g);
		
		for(JLine line : lines)
			line.paint(g);
	}

}
