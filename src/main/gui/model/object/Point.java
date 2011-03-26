package main.gui.model.object;

import java.awt.Color;
import java.awt.Graphics;

public class Point {
	private static int next = 0;
	private final int id = next++;
	private int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(x, y, 10, 10);
		g.setColor(Color.BLACK);
		g.drawOval(x, y, 10, 10);
	}
}
