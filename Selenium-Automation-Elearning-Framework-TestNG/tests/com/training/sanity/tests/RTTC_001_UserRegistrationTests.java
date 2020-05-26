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
import com.training.pom.LoginPOM;
import com.training.pom.RegSuccessPagePOM;
import com.training.pom.RetailHomepagePOM;
import com.training.pom.RetailLoginPOM;
import com.training.pom.UserRegistrationPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.RetailHomepagePOM;
import com.training.pom.RetailLoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_001_UserRegistrationTests {

	private WebDriver driver;
	private String baseUrl;

	private RetailHomepagePOM retailhomepagePOM;
	private RetailLoginPOM retailloginpagePOM;
	private UserRegistrationPOM userregPOM;
	private RegSuccessPagePOM regsucspgPOM;

	private static Properties properties;
	private ScreenShot screenShot;

	// @BeforeClass
	@BeforeSuite
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	// @BeforeMethod
	@BeforeClass
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		
		retailloginpagePOM = new RetailLoginPOM(driver);
		retailhomepagePOM = new RetailHomepagePOM(driver);
		userregPOM = new UserRegistrationPOM(driver);
		regsucspgPOM = new RegSuccessPagePOM(driver);
		
		baseUrl = properties.getProperty("baseURL");
		// screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}

	
	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	// This method is to open the Login page by click on the Account ID on the home
	// page
		@Test(priority = 0)
		public void openLoginPage() throws InterruptedException {
		System.out.println("Inside the Home page, about to click the account ID");
		Thread.sleep(5000);
		retailhomepagePOM.clickAccountIcon();
		}
		
		//This method is to open the Registration page by clicking on the Register button
		@Test(priority = 1)
		public void openRegistrationPage() {
			System.out.println("Inside the Login page, about to click on the Register button");
			retailloginpagePOM.clickRegisterBtn();
			
		}
		
		

	// This method is to provide all the required details to the Registration page.
	@Test(dependsOnMethods = "openRegistrationPage")
	public void enterRegistrationDetails() throws InterruptedException {
		userregPOM.enterFirstName("sandy");
		userregPOM.enterLastname("D");
		userregPOM.enterEmail("efg5@efg.com");
		userregPOM.enterTelep("12345678");

		userregPOM.enterAddress1("NewTown");
		userregPOM.enterAddress2("Kolkata");
		userregPOM.enterCity("Kolkata");
		userregPOM.enterPostCode("7000102");

		userregPOM.selectCountry("India");
		userregPOM.selectState("Sikkim");

		userregPOM.enterPassword("staysafe1");
		userregPOM.enterConfirmPswd("staysafe1");

		userregPOM.selectNewsLetter("No");

		userregPOM.selectPrivacyPolicy();
		userregPOM.clickContinueButton();

		String expregmsg = "Congratulations! Your new account has been successfully created!";
		

		//String actregmsg = regsucspgPOM.regsuccessmsg.getText();
		String actregmsg = regsucspgPOM.checkSuccessMsg();
//		System.out.println("In the main function, the RETURNED Actual string is :" + actregmsg);
//		System.out.println("In the main function, the Expected string is :" + expregmsg);
		Assert.assertEquals(actregmsg,expregmsg);
		Thread.sleep(3000);
 		regsucspgPOM.checkContinueBtn();
		// screenShot.captureScreenShot("First");

	}
	
	}

