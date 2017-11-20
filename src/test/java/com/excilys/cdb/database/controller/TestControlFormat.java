package com.excilys.cdb.database.controller;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.excilys.cdb.database.validator.FormatValidation;

public class TestControlFormat {
	private final FormatValidation controlFormat;
	
	
	public TestControlFormat(FormatValidation controlFormat) {
		super();
		this.controlFormat = controlFormat;
	}


	@Test
	public void test() {
		
		assertEquals(controlFormat.stringTolong("10"),Long.valueOf(10));
		assertEquals(controlFormat.stringTolong("dix"),null);
	}

}
