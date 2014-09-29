package ar.edu.utn.aaded.swing.container.table;

import java.lang.reflect.Field;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ar.edu.utn.aadeed.model.JAFieldDescription;

public class JARecordTableModel<T> extends AbstractTableModel {

	private static final long serialVersionUID = -9095681663487846176L;

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
		return getFieldValue(fieldName, item);
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

	private Object getFieldValue(String fieldName, T item) {
		try {
			Class<?> clazz = item.getClass();
			Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			return field.get(item);
		} catch (Exception e) {
			String errorMsg = String.format("Could not get %s value", fieldName);
			throw new RuntimeException(errorMsg, e);
		}
	}
}
