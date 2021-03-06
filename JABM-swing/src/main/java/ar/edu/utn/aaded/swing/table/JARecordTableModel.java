package ar.edu.utn.aaded.swing.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import ar.edu.utn.aadeed.JAReflections;
import ar.edu.utn.aadeed.model.JAFieldDescription;

public class JARecordTableModel<T> extends AbstractTableModel {

	private static final long serialVersionUID = -9138784218808144314L;

	private List<T> items;

	private List<JAFieldDescription> fields;

	public JARecordTableModel(List<JAFieldDescription> fields, List<T> items) {
		this.items = items;
		this.fields = fields;
	}

	public int getRowCount() {
		return items.size();
	}

	public int getColumnCount() {
		return fields.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		String fieldName = fields.get(columnIndex).getName();
		T item = getItem(rowIndex);
		return JAReflections.getFieldValue(item, fieldName);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return fields.get(columnIndex).getClazz();
	}
	
	@Override
	public String getColumnName(int column) {
		return fields.get(column).getLabel();
	}

	public T getItem(int row) {
		return items.get(row);
	}
}