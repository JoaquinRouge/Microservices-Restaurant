package com.restarurant.microservice_table.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restarurant.microservice_table.model.Table;
import com.restarurant.microservice_table.repository.ITableRepository;

@Service
public class TableService implements ITableService {

	@Autowired
	private ITableRepository tableRepository;
	
	@Override
	public List<Table> getAllTables() {
		return tableRepository.findAll();
	}

	@Override
	public Table getTable(Long id) {
		return tableRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Table with id " + id +
						" not found."));
	}

	@Override
	public Table getTable(String name) {
		return tableRepository.findByUser(name)
				.orElseThrow(() -> new IllegalArgumentException("The user " + name 
						+ " has no reservations." ));
	}

	@Override
	public Table createTable(Table table) {
		return tableRepository.save(table);
	}

	@Override
	public void deleteTable(Long id) {
		if(tableRepository.existsById(id)) {
			tableRepository.deleteById(id);
		}else {
			throw new IllegalArgumentException("Table with id " + id 
					+ " not found." );
		}
	}

	@Override
	public Table updateTable(Table table) {
		
		Table tableFromBd = this.getTable(table.getId());
		
		tableFromBd.setSize(table.getSize());
		tableFromBd.setAvailable(table.isAvailable());
		tableFromBd.setUser(table.getUser());
		
		return tableRepository.save(tableFromBd);
		
	}

}
