package com.excilys.cdb.database.core;

import org.springframework.stereotype.Component;

public class CompanyDTO {
	private long id;
	private String name;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CompanyDTO(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public CompanyDTO(long id) {
		super();
		this.id = id;
	}
	public CompanyDTO() {
	}
	@Override
	public String toString() {
		return "id=" + id + " --> name=" + name ;
	}
	
	
}
