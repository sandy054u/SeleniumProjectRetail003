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

	public class RTTC_031_InvalidLogin {

		private WebDriver driver;
		private String baseUrl;
		
		private RetailHomepagePOM retailhomepagePOM;
		private RetailLoginPOM retailloginpagePOM;
		
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
		public void enterLoginDetails() throws InterruptedException {
		System.out.println("Inside the Home page, about to click the account ID");
		Thread.sleep(5000);
		
		retailloginpagePOM.enterEmail("CCC@efg.com");
		retailloginpagePOM.enterPassword("asdadsa");
		retailloginpagePOM.clickLoginBtn();
		
		String expinvalidloginmsg = "Warning: No match for E-Mail Address and/or Password." ;
		String actinvalidloginmsg = retailloginpagePOM.loginFailureMessage();
		System.out.println("Returned text from LOGIN POM is " + actinvalidloginmsg);
		Assert.assertEquals(actinvalidloginmsg,expinvalidloginmsg);
		
		}

	}
