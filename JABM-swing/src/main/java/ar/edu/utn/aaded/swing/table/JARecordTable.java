package ar.edu.utn.aaded.swing.table;

import static com.google.common.base.Preconditions.checkArgument;

import java.awt.Dimension;
import java.util.List;

import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.view.container.JAViewContainer;
import ar.edu.utn.aadeed.view.table.JAViewRecordTable;

public class JARecordTable<T> implements JAViewRecordTable<T> {

	private JTable table;

	private List<JAFieldDescription> fields;

	private  volatile JARecordTableModel<T> model;

	public JARecordTable(JTable table) {
		this.table = table;
	}

	public void refresh(List<T> items) {
		checkArgument(fields != null, "fields cannot be null");
		checkArgument(items != null, "items cannot be null");

		model = new JARecordTableModel<T>(fields, items);
		table.setModel(model);
	}

	public void render(JAViewContainer container) {
		JScrollPane panel = new JScrollPane(table);
		container.addMember(Box.createRigidArea(new Dimension(0, 10)));
		container.addMember(panel);
	}

	public void setColumns(List<JAFieldDescription> fields) {
		this.fields = fields;
	}

	public T getSelectedItem() {
		int rowSelected = table.getSelectedRow();
		return (rowSelected != -1) ? (model.getItem(rowSelected)) : null;
	}
}
