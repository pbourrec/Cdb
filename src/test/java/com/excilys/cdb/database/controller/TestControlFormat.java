package com.excilys.cdb.database.controller;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class TestControlFormat {

	@Test
	public void test() {
		
		assertEquals(ControlFormat.stringTolong("10"),Long.valueOf(10));
		assertEquals(ControlFormat.stringTolong("dix"),null);
	}

}
