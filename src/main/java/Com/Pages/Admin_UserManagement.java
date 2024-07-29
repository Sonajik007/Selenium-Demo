package Com.Pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Com.Utility.Library;

public class Admin_UserManagement {
	private static final Logger LOGGER = LogManager.getLogger(Admin_UserManagement.class);

	@FindBy(xpath = "//span[text()='Admin']")
	private WebElement admin;

	@FindBy(xpath = "//button[normalize-space()='Add']")
	private WebElement AddButton;

	@FindBy(xpath = "(//input[contains(@class,'oxd-input ')])[2]")
	private WebElement userName;

	@FindBy(xpath = "(//div[@class='oxd-select-text oxd-select-text--active'])[1]")
	private WebElement userRole;

	@FindBy(xpath = "//div[@role='listbox']//span")
	private List<WebElement> userRole_List;

	@FindBy(xpath = "//div[@role='listbox']//div[2]")
	private WebElement selectAdminfromURdropdown;

	@FindBy(xpath = "//div[contains(@class,'oxd-autocomplete-text-inpu')]//input")
	private WebElement employeeName;

	@FindBy(xpath = "(//div[@class='oxd-select-text--after'])[2]")
	private WebElement status;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement searchButton;

	@FindBy(xpath = "//button[@type='submit']//preceding-sibling::button")
	private WebElement reSetButton;

	@FindBy(xpath = "(//div[@class='oxd-table-cell oxd-padding-cell'])[2]")
	private WebElement getSearchUsername;

	@FindBy(xpath = "//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//span")
	private WebElement getTheSearchResult;

	@FindBy(xpath = "//div[@class=\"oxd-table-body\"]//div")
	private List<WebElement> tableData;

	@FindBy(xpath = "//div[@role='listbox']//div[1]")
	private WebElement select_1stHint;

	@FindBy(xpath = "//span[normalize-space()='Enabled']")
	private WebElement enable;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement saveButton;

	@FindBy(xpath = "//div[@id='oxd-toaster_1']")
	private WebElement toastMessage;

	@FindBy(xpath = "(//input[@type='password'])[1]")
	private WebElement password;

	@FindBy(xpath = "(//input[@type='password'])[2]")
	private WebElement confirmPassword;

	// =============================================================================

	public Admin_UserManagement(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// =============================================================================

	public void clickOnAdmin() {
		admin.click();
	}

	public void clickOnSearchButton() {
		searchButton.click();
	}

	public void clickOnAddButton() {
		AddButton.click();
	}
	
	public void clickOnSaveButton() {
		saveButton.click();
	}

	public String enterValidUserNameAndVerifySearchResult(String user) throws InterruptedException {
		LOGGER.info("Entering valid username and verifying search result");
		userName.sendKeys(user);
		clickOnSearchButton();
		Thread.sleep(4000);
		boolean status = false;
		for (WebElement record : tableData) {
			String allrecords = record.getText();
			System.out.println(allrecords);
			if (allrecords.contains("FMLName")) {
				status = true;
			}
			Assert.assertTrue(status, "Records do not match with search results");
			break;
		}
		return user;
	}

	public String enterInvalidUserNameAndVerifySearchFunctionality(String user) throws InterruptedException {
		LOGGER.info("Entering invalid username and verifying search functionality");
		Thread.sleep(10000);
		userName.sendKeys(user);
		clickOnSearchButton();
		String actualResult = getTheSearchResult.getText();
		System.out.println("Records Found With Invalid UserName: " + actualResult);
		Assert.assertEquals(actualResult, "No Records Found");
		return user;
	}

	public void enterAndVerifySearchEmployeeName(String employee) throws InterruptedException {
		LOGGER.info("Entering employee name and verifying search result");
		Thread.sleep(1000);
		employeeName.sendKeys(employee);
		Thread.sleep(2000);
		select_1stHint.click();
		clickOnSearchButton();
		boolean status = false;
		for (WebElement record : tableData) {
			String allrecords = record.getText();
			System.out.println(allrecords);
			Thread.sleep(1000);
			if (allrecords.contains("Charles Carter")) {
				status = true;
			}
			Assert.assertTrue(status, "Records do not match with search results");
		}
	}

	public void selectUserRoleFromDropdown() throws InterruptedException {
		LOGGER.info("Selecting user role from dropdown");
		userRole.click();
		int options = userRole_List.size();
		for (WebElement option : userRole_List) {
			String op = option.getText();
			if (op.equals("ESS")) {
				option.click();
				break;
			}
		}
		clickOnSearchButton();
		Thread.sleep(3000);
		String searchResult = getTheSearchResult.getText();
		System.out.println("Search Result Found: " + searchResult);
	}

	public void verifySearchResultWithTable() throws InterruptedException {
		LOGGER.info("Verifying search result with table data");
		Thread.sleep(2000);
		boolean status = false;
		for (WebElement record : tableData) {
			String allrecords = record.getText();
			System.out.println(allrecords);
			if (allrecords.contains("ESS")) {
				status = true;
			}
			Assert.assertTrue(status, "Records do not match with search results");
		}
	}

	public void selectStatusAsEnableAndVerifySearchResult() {
		LOGGER.info("Selecting status as enabled and verifying search result");
		status.click();
		enable.click();
		boolean status = false;
		for (WebElement record : tableData) {
			String allrecords = record.getText();
			System.out.println(allrecords);
			if (allrecords.contains("Enable")) {
				status = true;
			}
			Assert.assertTrue(status, "Records do not match with search results");
		}
	}

	public void fillAllTheFieldWithCombinationValidData(String user, String employee) throws InterruptedException {
		LOGGER.info("Filling all fields with valid data combination and verifying search result");
		userName.sendKeys(user);
		userRole.click();
		Thread.sleep(1000);
		selectAdminfromURdropdown.click();
		status.click();
		enable.click();
		clickOnSearchButton();
		boolean status = false;
		for (WebElement record : tableData) {
			String allrecords = record.getText();
			System.out.println(allrecords);
			if (allrecords.contains("Enable")) {
				status = true;
			}
			Assert.assertTrue(status, "Records do not match with search results");
			break;
		}
	}

	// add new user

	public void addUserUsingAllMandatoryFields(String employee) throws InterruptedException {
		LOGGER.info("Adding a new user with all mandatory fields");
		Thread.sleep(1000);
		AddButton.click();
		userRole.click();
		selectAdminfromURdropdown.click();
		employeeName.sendKeys(employee);
		Thread.sleep(2000);
		select_1stHint.click();
		status.click();
		enable.click();
		String randomUser = Library.createRandomString(5);
		userName.sendKeys(randomUser);
		Thread.sleep(1000);
		password.sendKeys("abc@123");
		confirmPassword.sendKeys("abc@123");
		saveButton.click();
		Thread.sleep(2000);
		String message = toastMessage.getText();
		boolean status = false;
		if (message.contains("Successfully Saved")) {
			status = true;
			System.out.println(message);
		}
		Assert.assertTrue(status, "Error found while saving the record");
		
		// Verify created entry in table
		Thread.sleep(5000);
		System.out.println(randomUser);
		userName.sendKeys(randomUser);
		clickOnSearchButton();
		for (WebElement record : tableData) {
			String allrecords = record.getText();
			System.out.println(allrecords);
			if (allrecords.contains(randomUser)) {
				status = true;
			}
			Assert.assertTrue(status, "Records do not match with search results");
			break;
		}
	}
}
