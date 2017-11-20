package com.excilys.cdb.database.controller;

import java.sql.Date;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

import com.excilys.cdb.database.validator.DataValidation;

public class TestDataControl {

	private final DataValidation dataControl;
	
	
	
	public TestDataControl(DataValidation dataControl) {
		super();
		this.dataControl = dataControl;
	}
	@Test
	public void testStringToLongIDCompany() {
		Scanner sc = new Scanner(System.in);
		Assert.assertEquals(dataControl.stringToLongIDCompany("1", sc), Long.valueOf(1L));
		Assert.assertEquals(dataControl.stringToLongIDCompany("", sc), Long.valueOf(0L));

	}
	@Test
	public void testStringToLongIDComputer() {
		Scanner sc = new Scanner(System.in);
		Assert.assertEquals(dataControl.stringToLongIDComputer("1", sc), Long.valueOf(1L));

	}
	@Test
	public void testConvertStringToTimpeStamp() {
		Date date= new Date(106, 00, 10);
		System.out.println(date);
		Assert.assertEquals(dataControl.convertStringToTimestamp("10/01/2006"),date);
		Assert.assertEquals(dataControl.convertStringToTimestamp(""), (Date) null);

	}

	@Test
	public void testStringToInt() {
		Scanner sc = new Scanner(System.in);
		Assert.assertEquals(dataControl.stringToInt("10", sc), Integer.valueOf(10));
	}
	
}
