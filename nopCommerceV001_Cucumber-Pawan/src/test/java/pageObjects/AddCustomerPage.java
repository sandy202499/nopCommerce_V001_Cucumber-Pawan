package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {
	 
public WebDriver ldriver;

public AddCustomerPage(WebDriver rdriver)
{
	ldriver=rdriver;
	PageFactory.initElements(rdriver, this);
}
	
	By lnkCustomers_menu=By.xpath("//a[@href='#']//p[contains(text(),'Customers')]");
	By lnkCustomers_menuitem=By.xpath("//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]");
	By btnAddnew=By.xpath("//a[@class='btn btn-primary']");
	
	By txtEmail=By.xpath("//input[@id='Email']");
	By txtPassword=By.xpath("//input[@id='Password']");
	
	By txtcustomerRoles=By.xpath("//div[@class='input-group-append input-group-required']");
	
	By lstitemAdministrators=By.xpath("//li[@title='Administrators']");
	By lstitemRegistered=By.xpath("//li[@title='Registered']");
	By lstitemGuests=By.xpath("//li[@title='Guests']");
	By lstitemVendors=By.xpath("//li[@title='Vendors']");
	By lstitemForum=By.xpath("//li[@title='Forum Moderators']");
	
	By drpmgrOfVendor=By.xpath("//select[@id='VendorId']");
	
	By rdMaleGender=By.id("Gender_Male");
	By rdFemaleGender=By.id("Gender_Female");
	
	By txtFirstName=By.xpath("//input[@id='FirstName']");
	By txtLastName=By.xpath("//input[@id='LastName']");
	By txtDob=By.xpath("//input[@id='DateOfBirth']");
	By txtCompanyName=By.xpath("//input[@id='Company']");
	By txtAdminContent=By.xpath("//textarea[@id='AdminComment']");
	By btnSave=By.xpath("//button[@name='save']");
	
	
	//Action Methods of all the above elements
	
	
	public String getPageTitle()
	{
		return ldriver.getTitle();
	}
	
	public void clickOnCustomerMenu()
	{
		ldriver.findElement(lnkCustomers_menu).click();
	}
	
	public void clickOnCustomerMenuItem()
	{
		ldriver.findElement(lnkCustomers_menuitem).click();
	}
	
	public void clickOnAddnew()
	{
		ldriver.findElement(btnAddnew).click();
	}
	
	public void setEmail(String email)
	{
		ldriver.findElement(txtEmail).sendKeys(email);
	}
	
	public void setPassword(String password)
	{
		ldriver.findElement(txtPassword).sendKeys(password);
	}
	
	public void setCustomerRoles(String role) throws InterruptedException
	{
		/*if(!role.equals("Vendors"))
		{
			//ldriver.findElement(By.xpath("//div[@class='input-group-append input-group-required']//ul[@class='select2-selection__rendered']"));
			//ldriver.findElement(By.xpath(""));
			//ldriver.findElement(By.xpath("div[@class='k-multiselect-wrap k-floatwrap']"));
		} */
		if(!role.equals("Vendors")) //If role is vendors should not delete Register as per req.
		{
		ldriver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click();
		}
		
		ldriver.findElement(txtcustomerRoles).click();  
		
		WebElement listitem;
		Thread.sleep(3000); 
		
		if(role.equals("Administrators"))
		{
			listitem=ldriver.findElement(lstitemAdministrators);
		}
		else if(role.endsWith("Guests"))
		{
			listitem=ldriver.findElement(lstitemGuests);
		}
		else if(role.endsWith("Registered"))
		{
			listitem=ldriver.findElement(lstitemRegistered);
		}
		else if(role.endsWith("Vendors"))
		{
			listitem=ldriver.findElement(lstitemVendors);
		}
		else 
		{
			listitem=ldriver.findElement(lstitemGuests);
		}
		
		listitem.click();
					//OR
		JavascriptExecutor js= (JavascriptExecutor)ldriver;
		js.executeScript("arguments[0].click();", listitem);
		
		
	}
	
	public void setManagerOfVendor(String value)
	{
		Select drp=new Select(ldriver.findElement(drpmgrOfVendor));
		drp.selectByVisibleText(value);
	}

	public void setGender(String gender) {
		
		if (gender.equals("Male"))
		{
			ldriver.findElement(rdMaleGender).click();
		}
		else if(gender.equals("Female"))
		{
			ldriver.findElement(rdFemaleGender).click();
		}
		else
		{
			ldriver.findElement(rdMaleGender).click();  //Default
		}
	}
	
	public void setFirstName(String fname)
	{
		ldriver.findElement(txtFirstName).sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		ldriver.findElement(txtLastName).sendKeys(lname);
	}
	
	public void setDob(String dob)
	{
		ldriver.findElement(txtDob).sendKeys(dob);
	}
	
	public void setCompanyName(String comname)
	{
		ldriver.findElement(txtCompanyName).sendKeys(comname);
	}
	
	public void setAdminContent(String content)
	{
		ldriver.findElement(txtAdminContent).sendKeys(content);
	}
	
	public void clickOnSave()
	{
		ldriver.findElement(btnSave).click();
	}
}
