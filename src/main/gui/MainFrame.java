package main.gui;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import main.gui.model.Graph;
import main.gui.view.CanvasPanel;
import main.gui.view.MainPanel;

/**
 * Główne okno programu, tworzymy w nim obiekt głównego panelu.
 */
public class MainFrame extends JFrame {
	private static final long serialVersionUID = -1966090415318542313L;

	@SuppressWarnings("unused")
	private Graph model;
	private MainPanel main;
	
	public MainFrame(Graph model) {
		super("Graph");
		
		this.model = model;
		this.main = new MainPanel();
		
		this.add(main);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(800,600));
		this.setLocationByPlatform(true);
		this.pack();
		this.setVisible(true);
	}
	
	public CanvasPanel getCanvas() {
		return main.getCanvas();
	}
	
	public void addNewPointListener(ActionListener listener) {
		main.addNewPointListener(listener);
	}
	
	public void addNewLineListener(ActionListener listener) {
		main.addNewLineListener(listener);
	}
	
}
