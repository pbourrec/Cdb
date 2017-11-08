package com.excilys.cdb.database.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SeleniumDatabase {
	  private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  
	  @Before
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    baseUrl = "http://localhost:8080/cdb";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	  
	  @Test
	  public void testSelenium() throws Exception {
	    driver.get(baseUrl + "/dashboard");

	    // On se rend page 1
	    driver.findElement(By.id("contentForm:pageText")).clear();
	    driver.findElement(By.id("contentForm:pageText")).sendKeys("2");
	    driver.findElement(By.id("contentForm:nextPage")).click();

	    // On est page 2, on va page 3
	    driver.findElement(By.id("contentForm:page3Button")).click();

	    // On sélectionne notre prochaine page dans la liste
	    new Select(driver.findElement(By.id("contentForm:pageList_input"))).selectByVisibleText("1");
	    driver.findElement(By.id("contentForm:nextPageButton")).click();

	    // On est de retour page 1, on passe en anglais
	    driver.findElement(By.id("headerForm:english_button")).click();

	    // Et on recommence le même enchainement
	  }

}
