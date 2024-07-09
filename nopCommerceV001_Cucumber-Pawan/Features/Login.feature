Feature: Login

@sanity
Scenario: Successfull Login with Valid Credentials
		Given User Launch Chrome Browser
		When User opens URL "https://admin-demo.nopcommerce.com/login"
		And User enters Email as "admin@yourstore.com" and password as "admin"
		And Click on Login
		Then Page Title should be "Dashboard / nopCommerce administration"
		When User clicks on Logout link
		Then Page Title should be "Your store. Login"
		And close browser
	
@regression	
Scenario Outline: Successfull Login with Valid Credentials
		Given User Launch Chrome Browser
		When User opens URL "https://admin-demo.nopcommerce.com/login"
		And User enters Email as "<email>" and password as "<password>"
		And Click on Login
		Then Page Title should be "Dashboard / nopCommerce administration"
		When User clicks on Logout link
		Then Page Title should be "Your store. Login"
		And close browser
		
		Examples:
				| email | password |
				| admin@yourstore.com | admin |
				| admin1@yourstore.com | admin |
				