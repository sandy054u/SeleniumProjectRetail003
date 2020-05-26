package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.ProductCartPagePOM;
import com.training.pom.ProductHomePagePOM;
import com.training.pom.RegSuccessPagePOM;
import com.training.pom.RetailHomepagePOM;
import com.training.pom.RetailLoginPOM;
import com.training.pom.UserRegistrationPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_032_AddProductToCart_PreLogin {

	private WebDriver driver;
	private String baseUrl;

	private RetailHomepagePOM retailhomepagePOM;
	private RetailLoginPOM retailloginpagePOM;
	private UserRegistrationPOM userregPOM;
	private RegSuccessPagePOM regsucspgPOM;
	private ProductHomePagePOM prodhomepagePOM;
	private ProductCartPagePOM prodcheckoutpagePOM;

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
		prodhomepagePOM = new ProductHomePagePOM(driver);
		prodcheckoutpagePOM = new ProductCartPagePOM(driver);
		
		baseUrl = properties.getProperty("baseURL");
		// screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}
	
	//scroll down till the Best seller section of the home page
	@Test(priority = 0)
	public void scrolldownFindElement() throws InterruptedException { 
		
		//scrolling the page till the element Best Seller section
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", retailhomepagePOM.bestsellersection);
        
        //click on the product
        Actions act = new Actions(driver);
        act.moveToElement(retailhomepagePOM.prodquickviewbtn).build().perform();
        retailhomepagePOM.clickProduct();
	}
	
//	//select the product from the Best Seller sections
//	@Test(priority = 1)
//	public void selectProdct() throws InterruptedException {
//	retailhomepagePOM.prodquickviewbtn.click();
//	Thread.sleep(3000);
//		
//	}
	
	//Add the selected product to the Cart 
	@Test(priority = 2)
	public void clickAddtoCartButton() throws InterruptedException {
		//click on the Add to Cart button beside the product
	   prodhomepagePOM.clickAddtoCartBtn();
	   //Verify the Success message
	   String expmsg = "Success: You have added Integer Vitae Iaculis Massa to your shopping cart!";
	   String actmsg = prodhomepagePOM.verifySuccessMsg();
	   System.out.println("actual message is : " + actmsg);
	   Assert.assertEquals(actmsg, expmsg);
	 }
	 
	@Test(priority = 3)
	public void accessCart() {
		//Hover on the Cart icon 
		Actions actcarticon = new Actions(driver);
		actcarticon.moveToElement(prodhomepagePOM.carticon).build().perform();
		
		//Verify if the same product name is displayed in the light view layer
		//String prodnameprodpage  = prodhomepagePOM.prodtitleprodpage.getText();
		
		
		//move the pointer to product name on the light view window.
//		Actions actcarticon1 = new Actions(driver);
//		actcarticon1.moveToElement(prodhomepagePOM.proddesclightviewlayer).build().perform();
	//	String prodnamelightviewLayer = prodhomepagePOM.proddesclightviewlayer.getText();
		
		//System.out.println("Product Name  in Prod Page :" + prodnameprodpage);
		//System.out.println("Product name in the light view layer :" + prodnamelightviewLayer);
		//Assert.assertEquals(prodnameprodpage, prodnamelightviewLayer);
		
		//click on the View Cart button of the light view layer.
		prodhomepagePOM.clickViewCartBtn();
		
	}
	
	//Verify the product details in the Cart page
	@Test(enabled= false)
	public void verifyProductDetailsTable() {
		//String prodnameprodpage  = prodhomepagePOM.prodtitleprodpage.getText();
		//WebElement mytable = driver.findElement(By.xpath( "//div[@class='cart-info tb_min_w_500']//table[@class='table']"));
		String prodnm = "Lorem ipsum dolor sit amet";
		
		//Locate the rows of the table
        List<WebElement> rowsprodtable = prodcheckoutpagePOM.carttable.findElements(By.tagName("tr"));
        
        //calculate the number of rows in the table
        int rowscount = rowsprodtable.size();
        System.out.println("Number of rows in the table is :" + rowscount);
        
        for (int row=1; row<rowscount ; row++)
        {
        	List<WebElement>Columns_row=rowsprodtable.get(row).findElements(By.tagName("td"));
        	
        	int columns_count=Columns_row.size();             
        	//System.out.println("Number of cell in row"+row+  "are"  +columns_count); 
        	
        	for(int column=1;column<columns_count;column++){                 
        		String celltext=Columns_row.get(column).getText();
        		System.out.println("Table value is :" + celltext);
        		Assert.assertTrue(prodnm.contains(celltext));
//        		
//        		if (celltext == "Lorem ipsum dolor sit amet") {
//        			System.out.println("Product name matched");
//        			break;
//        		}
//        		else
//        			System.out.println("Selected product is not added to the Cart, please check it again");
//        		
        		System.out.println("Cell value of row number :" +row+ "and column number :" +column+ " Is " +celltext);             } 
        }
	}
	
	@Test(priority = 4)
	public void clickcheckout() throws InterruptedException {
		Thread.sleep(2000);
		prodcheckoutpagePOM.cartpagecheckoutbtn.click();
		
		//check if its redirecting to the Login page or not.
		String actpgurl = driver.getCurrentUrl();
		String expurl = "http://retail.upskills.in/checkout/checkout";
		System.out.println("Redirecting to the page with URL: " + actpgurl);
		Assert.assertEquals(actpgurl, expurl);
	}
	
	@Test(priority = 5)
	public void closeBrowser() {
		driver.quit();
	}

}

