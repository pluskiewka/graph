	package main.model.object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Line {
	private Point left, right; 
	private int color, level;
    private final static int width = 2;
	
	public Line(Point left, Point right, int level) {
		this.left = left;
		this.right = right;
		this.level = level;
		this.color = 0;
	}
	
	public boolean equals(Object obj) {
		return (obj instanceof Line &&
				((Line)obj).left.equals(this.left) && ((Line)obj).right.equals(right));
	}
	
	public int hashCode() {
		return left.hashCode()+right.hashCode();
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public int getColor() {
		return color;
	}
	
	public Point getLeft() {
		return left;
	}
	
	public Point getRight() {
		return right;
	}
	
	public void paint(Graphics g) {
		BasicStroke bs = new BasicStroke(width);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(bs);
		g2.setColor(new Color(color, color, color));
		g2.drawLine(left.getX()+5, left.getY()+5, right.getX()+5, right.getY()+5);
	}
}