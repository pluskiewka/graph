package main.gui.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import main.gui.model.object.Line;
import main.gui.model.object.Point;

public class Graph {
	private static Integer next = 0;
	private Set<Line> lines;
	private Set<Point> points;
	private int min, max;
	
	public static int getAndSetNext() {
		synchronized(next) {
			int ret = next;
			next++;
			return ret;
		}
	}
	
	public static int getNext() {
		return next;
	}
	
	public static void setNext(int next) {
		Graph.next = next;
	}
	
	public Graph() {
		lines = new HashSet<Line>();
		points = new HashSet<Point>();
		min=0;
		max=0;
	}
	
	public void addPoint(int id, int x, int y) {
		points.add(new Point(id, x, y));
	}
	
	public void addLine(Point left, Point right, int level) {
		/* Jesli dojdzie do zmiany, wymagane przekolorwanie grafu */
		boolean t = (level<min) || (level>max);
		
		if(level<min)
			min = level;
		else if(level>max)
			max = level;
		
		Line line = new Line(left, right, level);
		lines.add(line);
		
		if(t)
			/* Przekolorowanie globalne */
			setColor();
		else
			/* Ustawienie koloru lokalne */
			line.setColor((int)(255.0-255.0*((double)line.getLevel()-(double)min)/((double)(max-min))));
	}
	
	public Collection<Point> getPoints() {
		return points;
	}
	
	public Collection<Line> getLines() {
		return lines;
	}
	
	public void setLevel(int level, Line line) {
		/* Nastąpi zmiana wartości skrajnych */
		boolean b = (line.getLevel() == min) || (line.getLevel() == max);
		/* Jesli dojdzie do zmiany, wymagane przekolorwanie grafu */
		boolean t = (level<min) || (level>max);
		
		/* Zmiana bedzie lokalna */
		if(!b) {
			if(level<min)
				min = level;
			if(level>max)
				max = level;
		}
		
		/* Ustawienie nowe wartosc */
		line.setLevel(level);
		
		/* Nastpila zmiana granic */
		if(b)
			setMinMax();
		
		if(t || b)
			/* Przekolorowanie globalne */
			setColor();
		else
			/* Ustawienie koloru lokalne */
			line.setColor((int)(255.0-255.0*((double)line.getLevel()-(double)min)/((double)(max-min))));
	}
	
	/* Wołana tylko w przypadkach gdy nastąpi zmiana wagi krawędzi której waga była min/max */
	public void setMinMax() {
		List<Line> lines = new LinkedList<Line>(this.lines);
		this.min = lines.get(0).getLevel();
		this.max = this.min;
		
		for(Line line : lines) {
			int level=line.getLevel();
			if(level<min)
				min=level;
			if(level>max)
				max=level;
		}
	}
	
	/* Przekolorowanie grafu globalne */
	public void setColor() {
		for(Line line : lines) {
			line.setColor((int)(255.0-255.0*((double)line.getLevel()-(double)min)/((double)(max-min))));
		}
	}
	
}
