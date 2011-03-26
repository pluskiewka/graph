package main.gui.view;

import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Główny panel zawiera w sobie canvas, gdzie to umieszczany jest graf, tj.
 * kolejne jego punkty (JPoint, dekorujące Point) oraz krawędzie (JLine, dekorujące Line).
 * Umieszczanie punktów jak i łączenie ich jest dokonywane przez przyciski, jednakże fajnie by było
 * wykonać to na poziomie canvas.
 */
public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1207769589744868465L;
	private static final String NEW_POINT = "New point";
	private static final String NEW_LINE = "New line";
	
	private JScrollPane scrollPane;
	private CanvasPanel canvas;
	private JButton newPoint, newLine;
	
	public MainPanel() {
		this.initComponents();
		this.setLayout();
	}
	
	/**
	 * Ustanawia listener na przycisku nowej punktów/wierzchołka.
	 */
	public void addNewPointListener(ActionListener listener) {
		newPoint.addActionListener(listener);
	}
	
	/**
	 * Ustanawia listener na przycisku nowej linii/krawędzi.
	 */
	public void addNewLineListener(ActionListener listener) {
		newLine.addActionListener(listener);
	}
	
	/**
	 * Zwraca canvas.
	 * FIXME naruszona enkapusalcja obiektu.
	 */
	public CanvasPanel getCanvas() {
		return canvas;
	}
	
	/**
	 * Utworzenie obiektów jakie zawiera w sobie główny panel.
	 */
	private void initComponents() {
		this.newPoint = new JButton(NEW_POINT);
		this.newLine = new JButton(NEW_LINE);
		this.canvas = new CanvasPanel();
		this.scrollPane = new JScrollPane(canvas);
	}
	
	/**
	 * Ustawienie położenia obiektów na panelu.
	 */
	private void setLayout() {
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(layout.createSequentialGroup()
						.addComponent(newPoint)
						.addComponent(newLine))
					.addComponent(scrollPane));
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(newPoint)
						.addComponent(newLine))
					.addComponent(scrollPane));
	}
}
