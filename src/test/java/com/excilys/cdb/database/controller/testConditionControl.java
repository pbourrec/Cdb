package com.excilys.cdb.database.controller;

import org.junit.Assert;
import org.junit.Test;

public class testConditionControl {
	
	@Test()
	public void testisIdCompanyValid() {
//		   one = Mockito.mock(ControlFormat.class);
		Assert.assertTrue(ConditionControl.isIdCompanyValid("1"));
		Assert.assertFalse(ConditionControl.isIdCompanyValid("Quatorze"));
	}

	@Test()
	public void testisIdComputerValid() {
		Assert.assertTrue(ConditionControl.isIdComputerValid("1"));
		Assert.assertFalse(ConditionControl.isIdComputerValid("Quatorze"));
	}

}
