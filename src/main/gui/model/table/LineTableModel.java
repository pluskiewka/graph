package main.gui.model.table;

import javax.swing.table.AbstractTableModel;

import main.gui.view.CanvasPanel;
import main.model.Graph;

/**
 * Model danych tabelarycznych, opakowywujący główny model.
 * Tutaj: jest to model dla tabeli przechowywującej informacje 
 * o krawędziach.
 */
public class LineTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 7667866373992078041L;

	private Graph model;
	private CanvasPanel canvas;
	
	public LineTableModel(Graph model, CanvasPanel canvas) {
		this.model = model;
		this.canvas = canvas;
	}

	@Override
	public String getColumnName(int column) {
		switch(column) {
		case 0: return "Point 1";
		case 1: return "Point 2";
		case 2: return "Weight";
		case 3: return "Color";
		}
		return null;
	}
	
	@Override
	public int getRowCount() {
		return model.getLines().size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
			case 0: return model.getLines().get(rowIndex).getLeft().getId();
			case 1: return model.getLines().get(rowIndex).getRight().getId();
			case 2: return model.getLines().get(rowIndex).getLevel();
			case 3: return model.getLines().get(rowIndex).getColor();
		}
		return null;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if(columnIndex == 2) {
			model.setLevel(Integer.parseInt(aValue.toString()), model.getLines().get(rowIndex));
			this.fireTableDataChanged();
			canvas.repaint();
		}
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex == 2)
			return true;
		return false;
	}

}
