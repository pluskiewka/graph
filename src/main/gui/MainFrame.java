package main.gui;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import main.gui.controller.MainController;
import main.gui.model.Graph;
import main.gui.model.table.LineTableModel;
import main.gui.model.table.PointTableModel;
import main.gui.view.CanvasPanel;

/**
 * Główne okno programu, tworzymy w nim obiekt głównego panelu.
 */
public class MainFrame extends JFrame {
	private static final long serialVersionUID = -1966090415318542313L;
	
	private static final String LOAD_GRAPH = "Load graph";
	private static final String SAVE_GRAPH = "Save graph";
	
	private Graph model;
	private JPanel mainPanel;
	private JTable pointTable, lineTable;
	private PointTableModel pointTableModel;
	private LineTableModel lineTableModel;
	private JScrollPane scrollPane, pointScrollPane, lineScrollPane;
	private CanvasPanel canvas;
	private JButton loadButton, saveButton;
	
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
		
		new MainController(model, pointTableModel, lineTableModel, this);
	}
	
	public CanvasPanel getCanvas() {
		return canvas;
	}
	
	public JPanel getMainPanel() {
		return mainPanel;
	}
	
	public void addLoadButtonListener(ActionListener listener) {
		loadButton.addActionListener(listener);
	}
	
	public void addSaveButtonListener(ActionListener listener) {
		saveButton.addActionListener(listener);
	}
	
	/**
	 * Utworzenie obiektów jakie zawiera w sobie główny panel.
	 */
	private void initComponents() {
		this.mainPanel = new JPanel();
		this.canvas = new CanvasPanel(model);
		this.pointTableModel = new PointTableModel(model);
		this.lineTableModel = new LineTableModel(model);
		this.pointTable = new JTable(pointTableModel);
		this.lineTable = new JTable(lineTableModel);
		this.scrollPane = new JScrollPane(canvas);
		this.pointScrollPane = new JScrollPane(pointTable);
		this.lineScrollPane = new JScrollPane(lineTable);
		this.loadButton = new JButton(LOAD_GRAPH);
		this.saveButton = new JButton(SAVE_GRAPH);
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
				layout.createSequentialGroup()
					.addComponent(scrollPane)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, true)
							.addGroup(layout.createSequentialGroup()
									.addComponent(loadButton)
									.addComponent(saveButton))
							.addComponent(pointScrollPane, 50, 140, 160)
							.addComponent(lineScrollPane, 50, 140, 160)));
		
		layout.setVerticalGroup(
				layout.createParallelGroup()
					.addComponent(scrollPane)
					.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(loadButton)
									.addComponent(saveButton))
							.addComponent(pointScrollPane)
							.addComponent(lineScrollPane)));
	}
}
