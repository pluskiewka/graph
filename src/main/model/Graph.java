package main.model;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import main.model.object.Line;
import main.model.object.Point;

/**
 * Model grafu. Agreguje wierzchołki oraz krawędzie. Odpowiada
 * za operacje dot. ustalania kolorów krawędzi.
 */
public class Graph {
	private static Integer next = 0;
	private List<Line> lines;
	private List<Point> points;
	private int min, max;
	
	/**
	 * Zwraca informację o następnym, wolnym identyfikatorze, 
	 * który należy przypisać wierzchołkowi.
	 */
	public static int getAndSetNext() {
		synchronized(next) {
			int ret = next;
			next++;
			return ret;
		}
	}
	
	/**
	 * Zwraca wartość obecną, ostatnio przypisanego identyfikatora
	 * wierzchołka.
	 * @return
	 */
	public static int getNext() {
		return next;
	}
	
	/**
	 * Ustawia wartość, wołana przez FileImport, gdyż numeracja
	 * wierzchołków, zapisanego grafu może być inna, i należy 
	 * synchronizować istniejący już model oraz ładowany z pliku.
	 */
	public static void setNext(int next) {
		Graph.next = next;
	}
	
	/**
	 * Tworzy model grafu, tworzą odpowiednie kolekcje.
	 */
	public Graph() {
		lines = new LinkedList<Line>();
		points = new LinkedList<Point>();
		min=0;
		max=0;
	}
	
	/**
	 * Dodanie wierzchołka do modelu grafu, z podaniem wpsółrzędnych
	 * oraz identyfikatora wierzchołka.
	 * @param id
	 * @param x
	 * @param y
	 */
	public void addPoint(int id, int x, int y) {
		Point point = new Point(id, x, y);
		points.add(point);	
	}
	
	/**
	 * Dodanie krawędzi do grafu, z podaniem końców krawędzi oraz 
	 * jej wagi. Przy okazji, ustala się lokalnie zmiany dot. wartości zakresu
	 * wag krawędzi w modelu, służące do ustalenia odcienia szarości
	 * krawędzi. W zależności od tego, czy waga nowej krawędzi, jest
	 * jednym z zakresów, wymagane jest ustalenie dla wszystkich krawędzi
	 * nowej wartości odcienia szarości. 
	 * @param left
	 * @param right
	 * @param level
	 */
	public void addLine(Point left, Point right, int level) {
		Line line = new Line(left, right, level);
		lines.add(line);
		
		if( (level<min) || (level>max) ) {
			if(level<min)
				min = level;
			else if(level>max)
				max = level;
			setColor();
		} else {
			line.setColor((int)(255.0-255.0*((double)line.getLevel()-(double)min)/((double)(max-min))));
		}
	}
	
	public List<Point> getPoints() {
		return points;
	}
	
	public List<Line> getLines() {
		return lines;
	}
	
	public void setLevel(int level, Line line) {
		/* Nastąpi zmiana wartości skrajnych */
		boolean b = ((line.getLevel() == min) || (line.getLevel() == max)) && (level > min && level < max);
		/* Jesli dojdzie do zmiany, wymagane przekolorwanie grafu */
		boolean t = (level<min) || (level>max);
		
		/* Zmiana bedzie lokalna */
		if(t) {
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
		this.min = lines.get(0).getLevel();
		this.max = this.min;
		
		DecimalFormat format = new DecimalFormat("#.#########");
		
		long p1 = System.nanoTime();
		for(Line line : lines) {
			int level=line.getLevel();
			if(level<min)
				min=level;
			if(level>max)
				max=level;
		}
		long p2 = System.nanoTime();
		
		System.err.println("Time for minimax " + format.format((double)(p2-p1)/1000000000.0));
	}
	
	/* Przekolorowanie grafu globalne */
	public void setColor() {
		DecimalFormat format = new DecimalFormat("#.#########");
		
		long p1 = System.nanoTime();
		for(Line line : lines) {
			line.setColor((int)(255.0-255.0*((double)line.getLevel()-(double)min)/((double)(max-min))));
		}
		long p2 = System.nanoTime();
		
		System.err.println("Time for setting color " + format.format((double)(p2-p1)/1000000000.0));
	}
	
}
