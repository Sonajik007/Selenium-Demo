package Com.Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import Com.Utility.Library;

public class LoginPage {
	private WebDriver driver;

	
	@FindBy(xpath = "//input[@name='username']")
	private WebElement UserName;
	@FindBy(xpath = "//input[@name='password']")
	private WebElement PassWord;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement LoginButton;
	@FindBy(xpath = "//p[@class='oxd-userdropdown-name']")
	private WebElement VerifyLogin;
	@FindBy(xpath = "//a[text()='Logout']")
	private WebElement LogOut;
	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")
	private WebElement ErrorMsg;
	@FindBy(xpath = "//ul[@class='oxd-main-menu']//li")
	private List<WebElement> GetMenu;

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void enterUserName(String username) {
		UserName.sendKeys(username);
	}

	public void enterPassWord(String password) {
		PassWord.sendKeys(password);
	}

	public void clickOnLoginButton() {
		LoginButton.click();
	}

	public void clickOnLogOut() {
		VerifyLogin.click();
		LogOut.click();
	}

	public void getMenuList() {
		int getSize = GetMenu.size();
		System.out.println(getSize);
		for (int i = 0; i < GetMenu.size(); i++) {
			String MenuList = GetMenu.get(i).getText();
			System.out.println(MenuList);

			if (MenuList.contains("Dashboard")) {
				System.out.println("success");
			}
		}
	}

}
