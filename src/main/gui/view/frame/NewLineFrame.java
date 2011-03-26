package main.gui.view.frame;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.gui.view.object.JPoint;

class JPointItem {
	public final JPoint point;
	
	public JPointItem(JPoint point) {
		this.point = point;
	}
	
	public String toString() {
		return "Point " + point.getPoint().getId();
	}
}

class JPointComboBoxModel extends DefaultComboBoxModel {
	private static final long serialVersionUID = 3271616313594269107L;
	
	public JPointComboBoxModel(Collection<JPoint> points) {
		for(JPoint point : points) {
			this.addElement(new JPointItem(point));
		}
	}
}

public class NewLineFrame extends JFrame {
	private static final long serialVersionUID = 1486772894640883869L;

	private static final String TITLE = "Add line";
	private static final String INFO = "Adding new line to graph";
	private static final String LEVEL = "Level of new line";
	private static final String OKEY = "Okey";
	private static final String CANCEL = "Cancel";
	
	private Collection<JPoint> points;
	
	private JLabel infoLabel, levelLabel;
	private JTextField level;
	private JButton okeyButton, cancelButton;
	private JComboBox leftPoint, rightPoint;
	private JPanel panel;
	
	public NewLineFrame(Collection<JPoint> points) {
		super(TITLE);
		this.points = points;
		this.initComponents();
		this.setLayout();
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(480,145));
		this.setLocationByPlatform(true);
		this.pack();
		this.setVisible(true);
	}
	
	public void addCancelListener(ActionListener listener) {
		cancelButton.addActionListener(listener);
	}
	
	public void addOkeyListener(ActionListener listener) {
		okeyButton.addActionListener(listener);
	}
	
	public JPoint getLeftPoint() {
		return ((JPointItem)leftPoint.getSelectedItem()).point;
	}
	
	public JPoint getRightPoint() {
		return ((JPointItem)rightPoint.getSelectedItem()).point;
	}
	
	public Integer getLevel() {
		return Integer.parseInt(level.getText());
	}
	
	private void initComponents() {
		infoLabel = new JLabel(INFO);
		levelLabel = new JLabel(LEVEL);
		okeyButton = new JButton(OKEY);
		cancelButton = new JButton(CANCEL);
		
		level = new JTextField();
		JPointComboBoxModel
			pointModelLeft = new JPointComboBoxModel(points),
			pointModelRight = new JPointComboBoxModel(points);
		leftPoint = new JComboBox(pointModelLeft);
		rightPoint = new JComboBox(pointModelRight);
		
		panel = new JPanel();
	}
	
	private void setLayout() {
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(infoLabel)
					.addGroup(
							layout.createSequentialGroup()
								.addComponent(leftPoint)
								.addComponent(rightPoint))
					.addGroup(
							layout.createSequentialGroup()
								.addComponent(levelLabel)
								.addComponent(level))
					.addGroup(
							layout.createSequentialGroup()
								.addComponent(cancelButton)
								.addComponent(okeyButton)));
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
					.addComponent(infoLabel)
					.addGroup(
							layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(leftPoint)
								.addComponent(rightPoint))
					.addGroup(
							layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(levelLabel)
								.addComponent(level))
					.addGroup(
							layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(cancelButton)
								.addComponent(okeyButton)));
	}
}
