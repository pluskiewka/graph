package main.gui.controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import main.gui.model.table.LineTableModel;
import main.gui.model.table.PointTableModel;
import main.model.Graph;
import main.model.object.Point;

/**
 * Kontroler, czyli klasa, którego obiekt posiadający tutaj zdefiniowane metody
 * są one wołane w momencie wystąpienia zdarzenia na panelu rysującym graf.
 */
public class CanvasController {

	private Graph model;
	private Point point, toLine;
	private PointTableModel pointTableModel;
	private LineTableModel lineTableModel;
	private JPanel panel;
	
	/**
	 * Tworzy obiekt kontrolera, z podaniem wszelki modeli, jakie istnieją w programie,
	 * tj. modelu głównego, modeli tabel oraz referencji na panel gdzie rysowany jest graf.
	 * W zależności od zachodzących zdarzeń na panelu, wykonywane są odpowiedni operacje
	 * na modelu a następnie aktualizowany widok grafu, jak i tabel w głównym oknie.
	 * @param model
	 * @param pointTableModel
	 * @param lineTableModel
	 * @param panel
	 */
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
	
	/**
	 * Aktualizacja danych w tabeli.
	 */
	private void updateTableData() {
		pointTableModel.fireTableDataChanged();
		lineTableModel.fireTableDataChanged();
	}
	
	/**
	 * Aktualizacja widoku grafu.
	 */
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
			/* Nastąpiło zdarzenie przycisnięcia wskaźnika, gdy był on
			 * nad rysunkiem grafu. Następuje przeszukiwanie zbioru 
			 * wierzchołków, w celu ustalenia o których wierzchołek chodzi.
			 * Przyjmuje sie margines błędu do 10 pixeli. */
			for(Point p : model.getPoints()) {
				if(p.getX() > e.getX()-10 
						&& p.getX() < e.getX()+10 
						&& p.getY() > e.getY()-10 
						&& p.getY() < e.getY()+10) {
					point = p;
					/* Wierzchołek został znaleziony. */
					break;
				}
			}
			/* Jeśli wystąpiło tylko jedno przyciśnięcie, to mamy do 
			 * czynienia w zależności od poprzednich operacji, z 
			 * przemieszczaniem wierzchołka, tzw. dragging, albo z 
			 * wybranie drugiego wierzchołka do połączenia dwóch
			 * wybranych krawędzią. */
			if(e.getClickCount() == 1) {
				if(point != null && toLine == null)
					/* Wierzchołek będzie przemieszczany, gdyż nie został
					 * wybrany inny do połączenia ich krawędzią. */
					movePoint(e.getX()-5, e.getY()-5);
				else if(point != null && toLine != null) {
					/* Wierzchołek poprzedni został wcześniej wskazany,
					 * łączymy je krawędzią, dodając nową krawędź do
					 * modelu. */
					model.addLine(point, toLine, 0);
					toLine.setColor(Color.BLACK);
					/* Aktualizacja danych w tabelach oraz na rysunku */
					updateTableData();
					repaint();
					/* Przywrócenie wartości domyślnych  */
					point = null;
					toLine = null;
				}
			} else if (e.getClickCount() == 2) {
				if(point != null) {
					/* Dwukrotne kliknięcie oznacza albo wybór istniejącego 
					 * wierzchołka do połączenia go z innym, albo jeśli nie istenieje
					 * pod wskazywanym przez wskaźnik miejscem, wierzchołek,
					 * tworzy się nowy wierzchołek. Tutaj mamy do czynienia z
					 * wyborem wierzchołka na połączenie go krawędzia. */
					toLine = point;
					toLine.setColor(Color.RED);
					repaint();
				} else {
					/* Dodajemy nowy wierzchołek, podając identyfikator
					 * oraz współrzędne. */
					int next = Graph.getAndSetNext();
					model.addPoint(next, e.getX()-5, e.getY()-5);
					/* Aktualizujemy dane w tabelach oraz na panelu. */
					updateTableData();
					repaint();
					/* Przywracamy wartości domyślne. */
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
