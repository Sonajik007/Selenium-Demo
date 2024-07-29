package com.Test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Com.Pages.Admin_UserManagement;
import Com.Pages.LoginPage;
import Com.Utility.BaseClass;

public class Admin_UserManagement_Test extends BaseClass {
    Admin_UserManagement um;
    LoginPage login;

    @BeforeClass
    public void setUp() {
        um = new Admin_UserManagement(driver);
        login = new LoginPage(driver);

        login.enterUserName("Admin");
        login.enterPassWord("admin123");
        login.clickOnLoginButton();
        um.clickOnAdmin();
    }

    @BeforeMethod
    public void refreshBeforeEveryTestMethod() {
        driver.navigate().refresh();
    }

    @Test(priority = 1)
    public void verifySearchFunctionalityUsingValidUserName() throws InterruptedException {
        Thread.sleep(5000);
        um.enterValidUserNameAndVerifySearchResult("FMLName");
    }

    @Test(priority = 2)
    public void verifySearchFunctionalityUsingInvalidUserName() throws InterruptedException {
        um.enterInvalidUserNameAndVerifySearchFunctionality("abcdef");
    }

    @Test(priority = 3)
    public void verifySearchFunctionalityUsingValidUserRole() throws InterruptedException {
        um.selectUserRoleFromDropdown();
        um.verifySearchResultWithTable();
    }

    @Test(priority = 4)
    public void verifySearchFunctionalityUsingValidEmployeeHintName() throws InterruptedException {
        um.enterAndVerifySearchEmployeeName("Charles");
    }

    @Test(priority = 5)
    public void verifySearchFunctionalityUsingStatus() throws InterruptedException {
        um.selectStatusAsEnableAndVerifySearchResult();
    }

    @Test(priority = 6)
    public void verifySearchFunctionalityUsingCombinationOfAllValidFields() throws InterruptedException {
        um.fillAllTheFieldWithCombinationValidData("Admin", "КVishal Рsharma");
    }

    @Test(priority = 7)
    public void addAndVerifyUserUsingAllMandatoryFields() throws InterruptedException {
        um.addUserUsingAllMandatoryFields("Rahul das");
    }
}
