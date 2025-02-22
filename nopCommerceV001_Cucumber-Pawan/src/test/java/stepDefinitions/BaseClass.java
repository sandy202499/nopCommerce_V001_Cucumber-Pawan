package stepDefinitions;

import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class BaseClass {
	
	public WebDriver driver;
	public LoginPage lp;
	public AddCustomerPage addCust;
	public SearchCustomerPage searchCust;
	public static Logger logg;
	public Properties configProp;

	// Created for generating random string for unique email
	
	public static String randomstring()
	{
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return(generatedString1);
	}
}
