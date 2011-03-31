package main.gui.model.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import main.gui.model.Graph;
import main.gui.model.object.Line;

public class LineTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 7667866373992078041L;

	private Graph model;
	private List<Line> lines;
	
	public LineTableModel(Graph model) {
		this.model = model;
	}

	@Override
	public String getColumnName(int column) {
		switch(column) {
		case 0: return "Point 1";
		case 1: return "Point 2";
		case 2: return "Weight";
		}
		return null;
	}
	
	@Override
	public int getRowCount() {
		return model.getLines().size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) { 
		switch(columnIndex) {
		case 0: return lines.get(rowIndex).getLeft().getId();
		case 1: return lines.get(rowIndex).getRight().getId();
		case 2: return lines.get(rowIndex).getLevel();
		}
		return null;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if(columnIndex == 2)
			lines.get(rowIndex).setLevel(Integer.parseInt((String)aValue));
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex == 2)
			return true;
		return false;
	}
	
	@Override
	public void fireTableDataChanged() {
		super.fireTableDataChanged();
		lines  = new ArrayList<Line>(model.getLines());
	}

}
