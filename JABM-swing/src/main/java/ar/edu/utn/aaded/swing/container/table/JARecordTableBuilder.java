package ar.edu.utn.aaded.swing.container.table;

import javax.swing.JTable;

import ar.edu.utn.aadeed.view.container.table.JAViewRecordTable;
import ar.edu.utn.aadeed.view.container.table.JAViewRecordTableBuilder;

public class JARecordTableBuilder implements JAViewRecordTableBuilder {

	public <T> JAViewRecordTable<T> build() {
		JTable table = new JTable();
		return new JARecordTable<T>(table);
	}
}
