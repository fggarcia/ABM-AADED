package ar.edu.utn.aaded.swing.container.table;

import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.view.container.JAViewContainer;
import ar.edu.utn.aadeed.view.container.table.JAViewRecordTable;

public class JARecordTable<T> implements JAViewRecordTable<T> {

	private JTable table;
	
	private List<JAFieldDescription> fields;
	
	public JARecordTable(JTable table) {
		this.table = table;
	}

	public void refresh(List<T> items) {
		table.setModel(new JARecordTableModel<T>(fields, items));
	}

	public void render(JAViewContainer container) {
		JScrollPane panel = new JScrollPane(table);
		container.addMember(panel);
	}

	public void setColumns(List<JAFieldDescription> fields) {
		this.fields = fields;
	}
}
