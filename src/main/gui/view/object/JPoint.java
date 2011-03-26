package main.gui.view.object;

import java.awt.Color;
import java.awt.Graphics;

import main.gui.model.object.Point;

public class JPoint {
	private Point point;
	private int x, y, width, height;
	
	public JPoint(Point point, int x, int y) {
		this.point = point;
		this.x = x;
		this.y = y;
		this.width = 10;
		this.height = 10;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((point == null) ? 0 : point.hashCode());
		result = prime * result + x;
		result = prime * result + y;
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
		JPoint other = (JPoint) obj;
		if (point == null) {
			if (other.point != null)
				return false;
		} else if (!point.equals(other.point))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	public Point getPoint() {
		return point;
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
		g.fillOval(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawOval(x, y, width, height);
	}
}
