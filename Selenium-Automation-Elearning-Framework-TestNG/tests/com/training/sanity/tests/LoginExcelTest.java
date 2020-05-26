package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.RetailHomepagePOM;
import com.training.pom.RetailLoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class LoginExcelTest {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private RetailLoginPOM retailloginPOM;
	private RetailHomepagePOM retailhomepagePOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		
		loginPOM = new LoginPOM(driver);
		retailloginPOM = new RetailLoginPOM(driver);
		retailhomepagePOM = new RetailHomepagePOM(driver);
		
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}
	@Test(dataProvider="excel-inputs", dataProviderClass=LoginDataProviders.class)
	public void loginDBTest(String email, String pswd) throws InterruptedException {
//		loginPOM.sendUserName(userName);
//		loginPOM.sendPassword(password);
//		loginPOM.clickLoginBtn();
//		screenShot.captureScreenShot(userName);
		retailhomepagePOM.clickAccountIcon();
		 
		retailloginPOM.enterEmail(email);
		retailloginPOM.enterPassword(pswd);
		retailloginPOM.clickLoginBtn();
	}
}
