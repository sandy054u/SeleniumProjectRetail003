package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductHomePagePOM {

	private WebDriver driver;

	public ProductHomePagePOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	 
	
	//Locator of Add to cart button
	@FindBy (id = "button-cart") public WebElement prodaddtocartbtn;
	
	 //locator of the Success message in the flash window.
	@FindBy (xpath = "//ul[@class='noty_cont noty_layout_topRight']//p[1]") private WebElement successmsg;
	
	//locator of the Cart Icon
	@FindBy (xpath = "//i[@class='tb_icon ico-linea-ecommerce-bag']") public WebElement carticon;
 
	//locator of the View Cart button in the light view layer
	@FindBy (xpath = "//div[@class='checkout buttons']//a[contains(text(),'View Cart')]") private WebElement viewcartbtn;
	
	
	//locator of the product title in the product details page
	@FindBy(xpath = "//div[@id='PageTitleSystem_Trh8OwMq']/h1[contains(text(),'Lorem ipsum dolor sit amet')]") public WebElement prodtitleprodpage;
	
	//locator of the product name on the light view layer
	@FindBy(xpath = "//td[@class='name']//a[contains(text(),'Lorem ipsum dolor sit amet')]") public WebElement proddesclightviewlayer;
	
	
	
	public void clickAddtoCartBtn() throws InterruptedException {
		//driver.findElement(acc_icon).click();
		this.prodaddtocartbtn.click();
		Thread.sleep(3000);
		
	}
	
	public String verifySuccessMsg() {
		String str = this.successmsg.getText();
		return(str);
	}

	public void accessCart() {
		this.carticon.click();
	}
	
	public void clickViewCartBtn() {
		this.viewcartbtn.click();
	}
	
	 
}
 
	
	
	
	

 
