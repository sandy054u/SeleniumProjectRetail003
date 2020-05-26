package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductCartPagePOM {

	private WebDriver driver;

	public ProductCartPagePOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	 
	
	//Locator of Check out button on the Cart page
	@FindBy (xpath = "//div[@class='pull-right']//a[@class='btn btn-primary']") public WebElement cartpagecheckoutbtn;
	
	//locator of the Continue Shopping button
	@FindBy(xpath = "//div[@class='pull-left']//a[@class='btn btn-default']") private WebElement cartpagecontinueshoppingbtn;
	
	//locator of the table in the checkout page : 
	@FindBy(xpath = "//div[@class = 'cart-info tb_min_w_500']//table[@class='table']") public WebElement carttable;
	
	public void clickCheckOut() throws InterruptedException {
		 this.cartpagecheckoutbtn.click();
	}
	
	public void clickContinueShopping() {
		this.cartpagecontinueshoppingbtn.click();
	}
	 	 
}
 
