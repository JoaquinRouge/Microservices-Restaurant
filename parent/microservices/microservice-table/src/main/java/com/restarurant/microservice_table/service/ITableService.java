package com.restarurant.microservice_table.service;

import java.util.List;

import com.restarurant.microservice_table.model.Table;

public interface ITableService {
	List<Table> getAllTables();
	Table getTable(Long id);
	Table getTable(String name);
	Table createTable(Table table);
	void deleteTable(Long id);
	Table updateTable(Table table);
}
