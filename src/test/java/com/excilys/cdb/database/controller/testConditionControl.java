package com.excilys.cdb.database.controller;

import org.junit.Assert;
import org.junit.Test;

public class testConditionControl {
	private final ConditionControl conditionControl;
	
	
	public testConditionControl(ConditionControl conditionControl) {
		super();
		this.conditionControl = conditionControl;
	}

	@Test()
	public void testisIdCompanyValid() {
//		   one = Mockito.mock(ControlFormat.class);
		Assert.assertTrue(conditionControl.isIdCompanyValid("1"));
		Assert.assertFalse(conditionControl.isIdCompanyValid("Quatorze"));
	}

	@Test()
	public void testisIdComputerValid() {
		Assert.assertTrue(conditionControl.isIdComputerValid("1"));
		Assert.assertFalse(conditionControl.isIdComputerValid("Quatorze"));
	}

}
