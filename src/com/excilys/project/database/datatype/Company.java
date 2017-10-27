package com.excilys.project.database.datatype;

public class Company {
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
	public Company(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Company() {
	}
	@Override
	public String toString() {
		return "id=" + id + " --> name=" + name ;
	}
	
	
}
