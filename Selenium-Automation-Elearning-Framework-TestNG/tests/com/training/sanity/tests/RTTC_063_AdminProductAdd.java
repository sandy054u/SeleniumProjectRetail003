package com.training.sanity.tests;

	import java.io.FileInputStream;
	import java.io.IOException;
	import java.util.Properties;

import org.junit.AfterClass;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

	import com.training.generics.ScreenShot;
import com.training.pom.AdminDashboardPagePOM;
import com.training.pom.LoginPOM;
import com.training.pom.RegSuccessPagePOM;
import com.training.pom.RetailAdminLoginPagePOM;
import com.training.pom.RetailHomepagePOM;
import com.training.pom.RetailLoginPOM;
import com.training.pom.UserRegistrationPOM;
import com.training.utility.DriverFactory;
	import com.training.utility.DriverNames;

	public class RTTC_063_AdminProductAdd {

		private WebDriver driver;
		private String admbaseUrl;
		
		private RetailAdminLoginPagePOM retailadmloginpagePOM;
		private RetailHomepagePOM retailhomepagePOM;
		private AdminDashboardPagePOM admdashboardPOM ;
		private RetailLoginPOM userloginPOM;
		
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

			retailhomepagePOM = new RetailHomepagePOM(driver);
			retailadmloginpagePOM = new RetailAdminLoginPagePOM(driver);
			admdashboardPOM = new AdminDashboardPagePOM(driver);
			userloginPOM = new RetailLoginPOM(driver);
			
			admbaseUrl = properties.getProperty("adminBaseURL");
			//screenShot = new ScreenShot(driver); 
			// open the Admin page in the browser 
			driver.get(admbaseUrl);
		}
		
		 
		@Test(priority = 0)
		public void loginAdmin() throws InterruptedException {
		System.out.println("On the Login page, providing the login details");
		Thread.sleep(5000);
		
		retailadmloginpagePOM.enterAdmUserID("admin");
		retailadmloginpagePOM.enterAdmPassword("admin@123");
		Thread.sleep(3000);
		retailadmloginpagePOM.clickAdmLoginBtn();
		System.out.println("Logged in");
		
		}
		
		@Test(priority = 1)
		public void accessProductAddPage() throws InterruptedException {
			Thread.sleep(2000);
			admdashboardPOM.clickDashboardMenu();
			Thread.sleep(1000);
			admdashboardPOM.clickCatalogue();
			Thread.sleep(1000);
			//click on the Product link from the left panel
			admdashboardPOM.clickProduct();
			Thread.sleep(2000);
			//click on the + icon to add product
			admdashboardPOM.clickAddProdIcon();
			
			//Verify if it opens the Add product section or not
			String actprodlabel = admdashboardPOM.verifyAddProdLabel();
			String expprodlabel = "Add Product";
			System.out.println("Actual Retireved string is :" + actprodlabel);
			Assert.assertEquals(expprodlabel, actprodlabel);
			
			//add product name
			admdashboardPOM.enterProdName("Finger Ring");
			
			//add Meta Tag Title
			admdashboardPOM.enterMetaTagTitle("Finger Ring for ladies");
		
			//Click on the Data Tab and enter values on the fields
			admdashboardPOM.datatab.click();
			admdashboardPOM.enterModel("SKU-012");
			admdashboardPOM.enterPrice(500);
			admdashboardPOM.enterQuantity(10);
			
			//CLick on the Link tab and enter values in the required fields
			admdashboardPOM.clickLinks();
			
			//enter 'ear' in the Categories text box and select the same from the list box
			admdashboardPOM.enterCategory("Ear");
			Thread.sleep(2000);
			WebElement catlist = driver.findElement(By.xpath("//div[@class='panel-body']//div[2]//div[1]//ul[1]"));
			Actions act = new Actions(driver);
			act.moveToElement(catlist).build().perform();
			driver.findElement(By.xpath("//ul[@class='dropdown-menu']//li")).click();
			
			Thread.sleep(2000);
			
			//Click on the Save button and validate the success message
			admdashboardPOM.clickSaveBtn();
			
			String actsavemsg = admdashboardPOM.verifySuccessMsg();
			String expsavemsg = "Success";
			System.out.println("Actual Retireved string for the success message :" + actsavemsg);
			Assert.assertTrue(actsavemsg.contains(expsavemsg));
			
			Thread.sleep(3000);
			
			//click on the logout button
			admdashboardPOM.clickLogOut();
			Thread.sleep(1000);
			
		}
			@Test(priority = 2)
			public void userLogin() throws InterruptedException {
				driver.get("http://retailm1.upskills.in/");
				//retailhomepagePOM.clickAccountIcon();
				Thread.sleep(5000);
				retailhomepagePOM.enterSearch("EARRINGS");
				 
				
				
			}
		 
		

}