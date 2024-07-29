package com.Test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Com.Pages.LoginPage;

public class LoginTest extends Com.Utility.CrossBrowserClass {

	LoginPage login;

	@Test(dataProvider = "LoginTest", dataProviderClass = Com.Utility.Library.class)
	public void testLogin(String userName, String passWord) throws InterruptedException {
		login = new LoginPage(driver);

		login.enterUserName(userName);
		login.enterPassWord(passWord);
		login.clickOnLoginButton();
		login.getMenuList();
		login.clickOnLogOut();
	}

	@AfterMethod
	public void refreshAfterMethod() {
		driver.navigate().refresh();
	}

}
