package main.gui.model.object;

import java.awt.Color;
import java.awt.Graphics;

public class Line {
	private Point left, right; 
	private int level;
	
	public Line(Point left, Point right, int level) {
		this.left = left;
		this.right = right;
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
	public Point getLeft() {
		return left;
	}
	
	public Point getRight() {
		return right;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawLine(left.getX(), left.getY(), right.getX(), right.getY());
	}
}
