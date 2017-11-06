package com.excilys.cdb.database.datatype;

public class Company {
	Long id;
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Company(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Company(Long id) {
		super();
		this.id = id;
	}
	public Company() {
	}
	@Override
	public String toString() {
		return "id=" + id + " --> name=" + name ;
	}
	
	
}
