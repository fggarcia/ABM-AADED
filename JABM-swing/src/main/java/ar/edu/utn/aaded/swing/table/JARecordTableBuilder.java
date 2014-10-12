package ar.edu.utn.aaded.swing.table;

import javax.swing.JTable;

import ar.edu.utn.aaded.swing.table.JARecordTable;
import ar.edu.utn.aadeed.view.table.JAViewRecordTable;
import ar.edu.utn.aadeed.view.table.builder.JAViewRecordTableBuilder;

public class JARecordTableBuilder implements JAViewRecordTableBuilder {

	public <T> JAViewRecordTable<T> build() {
		JTable table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		return new JARecordTable<T>(table);
	}
}
