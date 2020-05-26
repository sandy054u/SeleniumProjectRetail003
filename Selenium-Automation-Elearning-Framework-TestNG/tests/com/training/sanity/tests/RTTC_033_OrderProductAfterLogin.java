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
import com.training.pom.CheckOutPagePOM;
import com.training.pom.LoginPOM;
import com.training.pom.OrderConfirmationPagePOM;
import com.training.pom.ProductCartPagePOM;
import com.training.pom.ProductHomePagePOM;
import com.training.pom.RegSuccessPagePOM;
import com.training.pom.RetailHomepagePOM;
import com.training.pom.RetailLoginPOM;
import com.training.pom.UserRegistrationPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_033_OrderProductAfterLogin {

	private WebDriver driver;
	private String baseUrl;

	private RetailHomepagePOM retailhomepagePOM;
	private RetailLoginPOM retailloginpagePOM;
	private UserRegistrationPOM userregPOM;
	private RegSuccessPagePOM regsucspgPOM;
	private ProductHomePagePOM prodhomepagePOM;
	private ProductCartPagePOM prodcartpagePOM;
	private CheckOutPagePOM checkoutpagePOM;
	private OrderConfirmationPagePOM orderconfirmPOM;
	
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
		prodcartpagePOM = new ProductCartPagePOM(driver);
		checkoutpagePOM = new CheckOutPagePOM(driver);
		orderconfirmPOM = new OrderConfirmationPagePOM(driver);
		
		baseUrl = properties.getProperty("baseURL");
		// screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}
	
	//login to the application
	@Test(priority = 0)
	public void openLoginPage() throws InterruptedException {
	Thread.sleep(5000);
	retailhomepagePOM.clickAccountIcon();
	Thread.sleep(5000);
	
	retailloginpagePOM.enterEmail("efg1@efg1.com");
	retailloginpagePOM.enterPassword("staysafe1");
	retailloginpagePOM.clickLoginBtn();
	
	//Directly send to the Product Entry page
	driver.get("http://retail.upskills.in/homes");
	}
	
	//Scroll down to the Best Seller section and from there click on any product
	@Test(enabled = false)
	public void scrolldownFindElement() throws InterruptedException { 
		
		//scrolling the page till the element Best Seller section
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", retailhomepagePOM.bestsellersection);
        
        //click on the product
        Actions act = new Actions(driver);
        act.moveToElement(retailhomepagePOM.prodquickviewbtn).build().perform();
        retailhomepagePOM.clickProduct();
	}
	
	//Select the product from the Best Sellers section
	@Test(enabled = false)
	public void selectProduct()
	{
		//Need to select the product from the Best Sellers section
		
	}
	
	//Click on the Add to Cart Button from the Product Details page
	@Test(priority = 1)
	public void clickAddtoCartButton() throws InterruptedException {
		//click on the Add to Cart button beside the product
	   prodhomepagePOM.clickAddtoCartBtn();
	   //Verify the Success message
	   String expmsg = "Success: You have added Lorem ipsum dolor sit amet to your shopping cart!";
	   String actmsg = prodhomepagePOM.verifySuccessMsg();
	   System.out.println("actual message is : " + actmsg);
	   Assert.assertEquals(actmsg, expmsg);
	 }
	
	//Mouse over to the Cart Icon and click on the View Cart Button
	@Test(priority = 2)
	public void accessCart() {
		//Hover on the Cart icon 
		Actions actcarticon = new Actions(driver);
		actcarticon.moveToElement(prodhomepagePOM.carticon).build().perform();
		
		//Verify if the same product name is displayed in the light view layer
		String prodnameprodpage  = prodhomepagePOM.prodtitleprodpage.getText();
		
		
		//move the pointer to product name on the light view window.
//		Actions actcarticon1 = new Actions(driver);
//		actcarticon1.moveToElement(prodhomepagePOM.proddesclightviewlayer).build().perform();
		String prodnamelightviewLayer = prodhomepagePOM.proddesclightviewlayer.getText();
		
		System.out.println("Product Name  in Prod Page :" + prodnameprodpage);
		System.out.println("Product name in the light view layer :" + prodnamelightviewLayer);
		//Assert.assertEquals(prodnameprodpage, prodnamelightviewLayer);
		
		//click on the View Cart button of the light view layer.
		prodhomepagePOM.clickViewCartBtn();
		
	}
	
	//Verify the product details in the Cart page
		@Test(enabled = false)
		public void verifyProductDetailsTable() {
			//String prodnameprodpage  = prodhomepagePOM.prodtitleprodpage.getText();
			//WebElement mytable = driver.findElement(By.xpath( "//div[@class='cart-info tb_min_w_500']//table[@class='table']"));
			String prodnm = "Lorem ipsum dolor sit amet";
			
			//Locate the rows of the table
	        List<WebElement> rowsprodtable = prodcartpagePOM.carttable.findElements(By.tagName("tr"));
	        
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
//	        		if (celltext == "Lorem ipsum dolor sit amet") {
//	        			System.out.println("Product name matched");
//	        			break;
//	        		}
//	        		else
//	        			System.out.println("Selected product is not added to the Cart, please check it again");
//	        		
	        		System.out.println("Cell value of row number :" +row+ "and column number :" +column+ " Is " +celltext);             } 
	        }
		}
	
		//Click on the CheckOut button of the Cart page
		@Test(priority = 3)
		public void clickCheckout() throws InterruptedException {
			Thread.sleep(2000);
			prodcartpagePOM.cartpagecheckoutbtn.click();
			
			//check if its redirecting to the Checkout page or not.
			String actpgurl = driver.getCurrentUrl();
			String expurl = "http://retail.upskills.in/checkout/checkout";
			System.out.println("Redirecting to the page with URL: " + actpgurl);
			Assert.assertEquals(actpgurl, expurl);
			
			//Check if the Billing section is expanded or not by checking the expanded attribute. it should be 'True'
		String billstatus = checkoutpagePOM.billingdetailsexpand.getAttribute("aria-expanded");
		System.out.println("Get Attribute :" + billstatus);
		
		//Click on the continue button to get to the Billing Address option
		checkoutpagePOM.clickContinueBillingDetBtn();
		Thread.sleep(1000);
		
		//Check if the Delivery address
		String address = checkoutpagePOM.deliveryadd.getText();
		System.out.println("Delivery Address is :" + address);
		Assert.assertTrue(address.contains("NewTown"));
		
		Thread.sleep(1000);
		
		
		//Click on Continue button the get the Free Shipping option
		checkoutpagePOM.clickContinueShipngAddBtn();
		
		//Check the FreeShipping option button
		Boolean optFreeshipping = checkoutpagePOM.freeshippingOpt.isSelected();
		System.out.println("Option Button Free shipping : " + optFreeshipping );
		//Assert.assertEquals(optFreeshipping, "true");
		
		
		
		//Add text to the Text Area
		checkoutpagePOM.addText("This is a nice product !");
			
		//Click on Continue button
		checkoutpagePOM.clickContinueShipngMethodBtn();	
		
		//click on the checkbox - Terms&Condition
		checkoutpagePOM.chckboxtermscondition.click();
		
		//Click on the Payment Method Continue button
		checkoutpagePOM.clickContinuePayMethodBtn();
		Thread.sleep(2000);
		
		//Click on the confirm button
		checkoutpagePOM.clickConfirmOrderBtn();
		Thread.sleep(2000);
		
		//Check the Success page
		String exporderconfmsg = "Your order has been successfully processed!";
		String actorderconfmsg =  orderconfirmPOM.orderconfirmSuccessmsg.getText();
		
		System.out.println("Your Success Message :" + actorderconfmsg);
		Assert.assertEquals(actorderconfmsg, exporderconfmsg);
				

		}
		
		
		
}

	
	
