package ar.edu.utn.aadeed.view.table.builder;

import ar.edu.utn.aadeed.view.table.JAViewRecordTable;

public interface JAViewRecordTableBuilder {

	public <T> JAViewRecordTable<T> build();

}