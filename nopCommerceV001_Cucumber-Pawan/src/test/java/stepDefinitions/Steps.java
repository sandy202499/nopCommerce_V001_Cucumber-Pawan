package stepDefinitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import stepDefinitions.BaseClass;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass {

	@Before // This is hook for cucumber, this method will execute only one time before test
			// case start
	public void setup() throws IOException {
		
		
		// logger=Logger.getLogger("nopCommerce");
		logg = Logger.getLogger("nopCommerce"); // Added logger
		PropertyConfigurator.configure("log4j.properties"); // Added logger
		
		// Reading properties
		configProp = new Properties();
		FileInputStream configPropfile = new FileInputStream("config.properties");
		configProp.load(configPropfile);
				
		String br=configProp.getProperty("browser");

		if(br.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
		driver = new ChromeDriver();
		}
		if(br.equals("firefox"))
		{
		System.setProperty("webdriver.chrome.driver", configProp.getProperty("firefoxpath"));
		driver = new FirefoxDriver();
		}
		else if(br.equals("edge"))
		{
			System.setProperty("webdriver.chrome.driver", configProp.getProperty("edgepath"));
			driver = new EdgeDriver();
		}
		logg.info("************ Launching Browser *************");
		logg.info("************ Test 3333333 *************");
	}

	// public WebDriver driver; // this variables moved to base class
	// public LoginPage lp; // this variables moved to base class

	@Given("User Launch Chrome Browser")
	public void user_Launch_Chrome_Browser() {

		lp = new LoginPage(driver);

	}

	@When("User opens URL {string}")
	public void user_opens_URL(String url) {

		logg.info("************ Launching URL *************");
		logg.info("************ Hello URL *************");
		driver.get(url);
		driver.manage().window().maximize();

	}

	@When("User enters Email as {string} and password as {string}")
	public void user_enters_Email_as_and_password_as(String email, String password) {
		logg.info("************ Providing login details *************");
		lp.setUserName(email);
		lp.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_Login() throws InterruptedException {
		logg.info("************ Started login *************");
		lp.clickLogin();
		Thread.sleep(3000);
	}

	@Then("Page Title should be {string}")
	public void page_Title_should_be(String title) throws InterruptedException {

		if (driver.getPageSource().contains("Login was unsuccessful.")) {
			System.out.println("Login Failed With Invalid Credentials:" + driver.getTitle());

			driver.close();
			logg.info("************ login passed *************");

			Assert.assertEquals(title, driver.getTitle());
			Assert.assertTrue(false);
		} else {
			logg.info("************ login failed *************");
			Assert.assertEquals(title, driver.getTitle());
		}
		Thread.sleep(3000);
	}

	@When("User clicks on Logout link")
	public void user_clicks_on_Logout_link() throws InterruptedException {
		logg.info("************ click on logout link *************");
		lp.clickLogout();
		Thread.sleep(3000);

	}

	@Then("close browser")
	public void close_browser() {
		logg.info("************ closing brower *************");
		driver.quit();

	}

// Customers feature step definitions..................................................................................

	@Then("User can view Dashboard")
	public void user_can_view_Dashboard() {

		addCust = new AddCustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());

	}

	@When("User click on customer Menu")
	public void user_click_on_customer_Menu() throws InterruptedException {
		Thread.sleep(3000);
		addCust.clickOnCustomerMenu();

	}

	@When("click on customers Menu Item")
	public void click_on_customers_Menu_Item() throws InterruptedException {

		Thread.sleep(2000);
		addCust.clickOnCustomerMenuItem();

	}

	@When("click on Add new button")
	public void click_on_Add_new_button() throws InterruptedException {

		addCust.clickOnAddnew();
		Thread.sleep(2000);

	}

	@Then("User can view Add new customer page")
	public void user_can_view_Add_new_customer_page() {

		Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());

	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		logg.info("************ Adding New Customer Details *************");
		String email = randomstring() + "@gmail.com";
		addCust.setEmail(email);
		addCust.setPassword("Test123");

		addCust.setCustomerRoles("Guest");
		Thread.sleep(2000);

		addCust.setManagerOfVendor("Vendor 2");
		addCust.setGender("Male");
		addCust.setFirstName("Sanju");
		addCust.setLastName("Sri");
		addCust.setDob("30/07/1980");
		addCust.setCompanyName("BusyQA");
		addCust.setAdminContent("This is for testing.......");

	}

	@When("click on Save button")
	public void click_on_Save_button() throws InterruptedException {
		logg.info("************ Saving customer data *************");
		addCust.clickOnSave();
		Thread.sleep(3000);
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg) {

		Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
				.contains("The new customer has been added successfully."));

	}

	// Searching a customer using Email
	// ID..................................................................................

	@When("Enter customer Email")
	public void enter_customer_Email() {
		// searchCust = new SearchCustomerPage(driver);
		logg.info("************ searching customer by email *************");
		searchCust = new SearchCustomerPage(driver);
		searchCust.setEmail("kiyjcycyhjc676008@gmail.com");
	}

	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {

		searchCust.clickSearch();
		Thread.sleep(3000);
	}

	@Then("User should found Email in the search table")
	public void user_should_found_Email_in_the_search_table() {

		boolean status = searchCust.searchCustomerByEmail("kiyjcycyhjc676008@gmail.com");

		Assert.assertEquals(true, status);
	}

	// Steps for searching a customer by using first name and last name............................................

	@When("Enter customer FirstName")
	public void enter_customer_FirstName() {
		logg.info("************ searching customer by name *************");
		searchCust = new SearchCustomerPage(driver);
		searchCust.setFirstName("Victoria");
	}

	@When("Enter customer LastName")
	public void enter_customer_LastName() {
		searchCust = new SearchCustomerPage(driver);
		searchCust.setLastName("Terces");
	}

	@Then("User should found Name in the search table")
	public void user_should_found_Name_in_the_search_table() {

		boolean status = searchCust.searchCustomerByName("Victoria Terces");
		Assert.assertEquals(true, status);
	}

}
