package Com.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;

public class Library {
	
	@DataProvider(name = "LoginTest")
	public static Object[][] getLoginData() throws IOException {
		DataFormatter formattor = new DataFormatter();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\LoginOpenHrm.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		int rowCount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int colCount = row.getLastCellNum();
		Object[][] data = new Object[rowCount - 1][colCount];
		for (int i = 0; i < rowCount - 1; i++) {
			row = sheet.getRow(i + 1);
			for (int j = 0; j < colCount; j++) {
				XSSFCell cell = row.getCell(j);
				data[i][j] = formattor.formatCellValue(cell);
			}
		}
		return data;
	}

	// WebDriverWait
	public static boolean isButtonDisplayed(WebDriver driver, WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement button = wait.until(ExpectedConditions.visibilityOf(ele));
		System.out.println("LogOut button is Displyed");
		return button.isDisplayed();
	}
	
	public static String createRandomString(int length) {
		 String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	        StringBuilder randomString = new StringBuilder(length);
	        Random random = new Random();
	        for (int i = 0; i < length; i++) {
	            randomString.append(characters.charAt(random.nextInt(characters.length())));
	        }
	        return randomString.toString();
	}
	
}
