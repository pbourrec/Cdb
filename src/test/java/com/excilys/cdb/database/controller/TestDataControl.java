package com.excilys.cdb.database.controller;

import java.sql.Date;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

public class TestDataControl {

	@Test
	public void testStringToLongIDCompany() {
		Scanner sc = new Scanner(System.in);
		Assert.assertEquals(DataControl.stringToLongIDCompany("1", sc), Long.valueOf(1L));
		Assert.assertEquals(DataControl.stringToLongIDCompany("", sc), Long.valueOf(0L));

	}
	@Test
	public void testStringToLongIDComputer() {
		Scanner sc = new Scanner(System.in);
		Assert.assertEquals(DataControl.stringToLongIDComputer("1", sc), Long.valueOf(1L));

	}
	@Test
	public void testConvertStringToTimpeStamp() {
		Date date= new Date(106, 00, 10);
		System.out.println(date);
		Assert.assertEquals(DataControl.convertStringToTimestamp("10/01/2006"),date);
		Assert.assertEquals(DataControl.convertStringToTimestamp(""), (Date) null);

	}

	@Test
	public void testStringToInt() {
		Scanner sc = new Scanner(System.in);
		Assert.assertEquals(DataControl.stringToInt("10", sc), Integer.valueOf(10));
	}
	
}
