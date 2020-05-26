package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.AccountpagePOM;
import com.training.pom.LoginPOM;
import com.training.pom.RegSuccessPagePOM;
import com.training.pom.RetailHomepagePOM;
import com.training.pom.RetailLoginPOM;
import com.training.pom.UserRegistrationPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_061_UserRegistrationExcel {

	private WebDriver driver;
	private String baseUrl;

	private RetailHomepagePOM retailhomepagePOM;
	private RetailLoginPOM retailloginpagePOM;
	private UserRegistrationPOM userregPOM;
	private RegSuccessPagePOM regsucspgPOM;
	private AccountpagePOM	accountpagePOM;

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
		accountpagePOM = new AccountpagePOM(driver); 
				
		baseUrl = properties.getProperty("baseURL");
		// screenShot = new ScreenShot(driver);
		// open the browser
//		driver.get(baseUrl);
	}

	
	
	// This method is to open the Login page by click on the Account ID on the home
	// page
//		@Test(priority = 0)
//		public void openLoginPage() throws InterruptedException {
//		System.out.println("Inside the Home page, about to click the account ID");
//		Thread.sleep(5000);
//		retailhomepagePOM.clickAccountIcon();
//		}
		
		//This method is to open the Registration page by clicking on the Register button
//		@Test(priority = 1)
//		public void openRegistrationPage() {
//			System.out.println("Inside the Login page, about to click on the Register button");
//			retailloginpagePOM.clickRegisterBtn();
//			
//		}
		
		

	// This method is to provide all the required details to the Registration page.
	@Test(dataProvider="excel-inputs", dataProviderClass=LoginDataProviders.class)
	public void enterRegistrationDetails(String fname, String lname, String email, String phone, String Add1, String Add2, String city, String pcode, String country, String region, String pswd, String confirmpswd ) throws InterruptedException {
		driver.get(baseUrl);
		//Click on AccountIcon of the Retail home page 
		retailhomepagePOM.clickAccountIcon();
		
		//Click on the Register button on the login page
		retailloginpagePOM.clickRegisterBtn();
		
		//Reading data from excel
		userregPOM.enterFirstName(fname);
		userregPOM.enterLastname(lname);
		userregPOM.enterEmail(email);
		userregPOM.enterTelep(phone);

		userregPOM.enterAddress1(Add1);
		userregPOM.enterAddress2(Add2);
		userregPOM.enterCity(city);
		userregPOM.enterPostCode(pcode);

		userregPOM.selectCountry(country);
		userregPOM.selectState(region);

		userregPOM.enterPassword(pswd);
		userregPOM.enterConfirmPswd(confirmpswd);

		userregPOM.selectNewsLetter("No");
		
		

		userregPOM.selectPrivacyPolicy();
		Thread.sleep(2000);
		userregPOM.clickContinueButton();
		Thread.sleep(5000);
		String expregmsg = "Congratulations! Your new account has been successfully created!";
		

		//String actregmsg = regsucspgPOM.regsuccessmsg.getText();
		String actregmsg = regsucspgPOM.checkSuccessMsg();
		Thread.sleep(2000);
		
		System.out.println("In the main function, the RETURNED Actual string is :" + actregmsg);
//		System.out.println("In the main function, the Expected string is :" + expregmsg);
		Assert.assertEquals(actregmsg,expregmsg);
		Thread.sleep(3000);
 		regsucspgPOM.checkContinueBtn();
		// screenShot.captureScreenShot("First");
 		
		 //Click on the Logout by hovering over the account icon
		 Actions actlogout = new Actions(driver);
		 actlogout.moveToElement(accountpagePOM.acccicon).build().perform();
		 Thread.sleep(5000);
		 System.out.println("Before clicking on the Logout button clickig on the Logount out option");
		 driver.findElement(By.xpath("//span[contains(text(),'LOGOUT')]")).click();
		 System.out.println("After clicking on the logout button");
		 Thread.sleep(3000);
		
		

	}
	@Test()
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	}



 
