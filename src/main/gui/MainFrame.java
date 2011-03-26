package main.gui;

import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.gui.controller.CanvasController;
import main.gui.model.Graph;
import main.gui.view.CanvasPanel;

/**
 * Główne okno programu, tworzymy w nim obiekt głównego panelu.
 */
public class MainFrame extends JFrame {
	private static final long serialVersionUID = -1966090415318542313L;
	
	private Graph model;
	private JPanel mainPanel;
	private JScrollPane scrollPane;
	private CanvasPanel canvas;
	
	public MainFrame(Graph model) {
		super("Graph");
		this.model = model;
		this.initComponents();
		this.setLayout();
		this.add(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(800,600));
		this.setLocationByPlatform(true);
		this.pack();
		this.setVisible(true);
		
		new CanvasController(model, canvas);
	}
	
	/**
	 * Utworzenie obiektów jakie zawiera w sobie główny panel.
	 */
	private void initComponents() {
		this.mainPanel = new JPanel();
		this.canvas = new CanvasPanel(model);
		this.scrollPane = new JScrollPane(canvas);
	}
	
	/**
	 * Ustawienie położenia obiektów na panelu.
	 */
	private void setLayout() {
		GroupLayout layout = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(scrollPane));
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
					.addComponent(scrollPane));
	}
}
