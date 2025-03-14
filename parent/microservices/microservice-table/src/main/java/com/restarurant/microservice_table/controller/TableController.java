package com.restarurant.microservice_table.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restarurant.microservice_table.model.Table;
import com.restarurant.microservice_table.service.ITableService;

@RestController
@RequestMapping("/table")
public class TableController {

	@Autowired
	private ITableService tableService;
	
	@GetMapping()
	public ResponseEntity<?> getAllTables(){
		return ResponseEntity.status(HttpStatus.OK).body(tableService.getAllTables());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getTable(@PathVariable Long id){
		try {
			Table table = tableService.getTable(id);
			return ResponseEntity.status(HttpStatus.OK).body(table);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<Object> getTable(@PathVariable String name){
		try {
			Table table = tableService.getTable(name);
			return ResponseEntity.status(HttpStatus.OK).body(table);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<Object> createTable(@RequestBody Table table){
		return ResponseEntity.status(HttpStatus.OK).body(tableService.createTable(table));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteMapping(@PathVariable Long id){
		try {
			tableService.deleteTable(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> updateTable(@RequestBody Table table){
		return ResponseEntity.status(HttpStatus.OK).body(tableService.updateTable(table));
	}
	
}
