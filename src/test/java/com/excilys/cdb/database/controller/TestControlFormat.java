package com.excilys.cdb.database.controller;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class TestControlFormat {
	private final ControlFormat controlFormat;
	
	
	public TestControlFormat(ControlFormat controlFormat) {
		super();
		this.controlFormat = controlFormat;
	}


	@Test
	public void test() {
		
		assertEquals(controlFormat.stringTolong("10"),Long.valueOf(10));
		assertEquals(controlFormat.stringTolong("dix"),null);
	}

}
