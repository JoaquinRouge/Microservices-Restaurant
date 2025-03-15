package com.restarurant.microservice_reservations.dto;

public class TableDto {
	private Long id;
	private int size;
	private boolean available;
	private String user;
	
	public TableDto() {
		
	}

	public TableDto(Long id, int size, boolean available, String user) {
		super();
		this.id = id;
		this.size = size;
		this.available = available;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	
}
