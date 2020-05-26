package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.ForgotPasswordPOM;
import com.training.pom.LoginPOM;
import com.training.pom.RegSuccessPagePOM;
import com.training.pom.RetailHomepagePOM;
import com.training.pom.RetailLoginPOM;
import com.training.pom.UserRegistrationPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_003_UserRecoverPswdTests {

	private WebDriver driver;
	private String baseUrl;
	
	private RetailHomepagePOM retailhomepagePOM;
	private RetailLoginPOM retailloginpagePOM;
	private ForgotPasswordPOM forgotpswdPOM;
	
	private static Properties properties;
	private ScreenShot screenShot;

	//@BeforeClass
	@BeforeSuite
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		retailloginpagePOM= new RetailLoginPOM(driver);
		retailhomepagePOM = new RetailHomepagePOM(driver);
		forgotpswdPOM = new ForgotPasswordPOM(driver);
		 			
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	@Test(priority = 0)
	public void openLoginPage() throws InterruptedException {
	System.out.println("Inside the Home page, about to click the account ID");
	Thread.sleep(5000);
	retailhomepagePOM.clickAccountIcon();
	}
	@Test(priority = 1)
	public void clickForgottenPassword() throws InterruptedException {
	System.out.println("about to click the Forgotten password link");
	Thread.sleep(1000);
	 
	retailloginpagePOM.clickForgottenPswdLnk();
	}
	
	@Test(priority = 2)
	public void enterRecoveryPasswordDetails() {
		forgotpswdPOM.enterRecoveryEmail("abc@abc.com");
		forgotpswdPOM.clickForgotContinueBtn();
		String expforgotmsg = "An email with a confirmation link has been sent your email address.";
		String actforgotmsg = retailloginpagePOM.fogotemailmsg.getText();
		Assert.assertEquals(actforgotmsg,expforgotmsg);
	}
}