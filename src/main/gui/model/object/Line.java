	package main.gui.model.object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import main.gui.model.Graph;

public class Line {
	private Point left, right; 
	private int level, rgb;
	private Graph model;
    private int width = 3;
	
	public Line(Point left, Point right, int level, Graph model) {
		this.left = left;
		this.right = right;
		this.level = level;
		this.model = model;
	}
	
	public boolean equals(Object obj) {
		return (obj instanceof Line &&
				((Line)obj).left.equals(this.left) && ((Line)obj).right.equals(right));
	}
	
	public int hashCode() {
		return left.hashCode()+right.hashCode();
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
	
	public int getColor() {
		return rgb;
	}
	
	public void updateColor() {
		double min = model.getMin(), max = model.getMax();
		this.rgb = 255-(int)((255.0)*((level - min)/(max - min)));
	}
	
	public void setLevel(int level) {
		this.level = level;
		this.model.setMinMax();
		this.updateColor();
	}
	
	public void paint(Graphics g) {
		BasicStroke bs = new BasicStroke(width);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(bs);
		g2.setColor(new Color(rgb, rgb, rgb));
		g2.drawLine(left.getX()+5, left.getY()+5, right.getX()+5, right.getY()+5);
	}
}