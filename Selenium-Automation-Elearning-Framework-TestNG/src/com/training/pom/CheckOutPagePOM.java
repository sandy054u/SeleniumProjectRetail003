package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOutPagePOM {

	private WebDriver driver;

	public CheckOutPagePOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
		
	//Locator of the Continue button for Shippingdetails
	@FindBy (id = "button-payment-address") private WebElement payaddcontbtn;
	
	//Locator of the Continue button for Shippingdetails
	@FindBy (id = "button-shipping-address") private WebElement shipngaddbconttn;
	
	//Locator of the continue button for Shipping Method
	@FindBy (id = "button-shipping-method") private WebElement shipngmethdcontbtn;
	
	//locator of the Continue button for Paymend Method
	@FindBy (id = "button-payment-method") private WebElement pymntmethodcontbtn;
	
	//Locator of the Confirm Order Button 
	@FindBy(id = "button-confirm") private WebElement confirmorderbtn;
	
		
	
	
	//Locator of the Billing Details section
	@FindBy(xpath = "//h4[@class='panel-title tb_bg_str_2']//a[@class='accordion-toggle']") public WebElement billingdetailsexpand;
	
	//Delivery Details in the checkout page
	@FindBy (name = "address_id") public WebElement deliveryadd;
	
	//Delivery Method Free Shipping option button selected by default in the Checkout page
	@FindBy (name = "shipping_method") public WebElement freeshippingOpt;
	
	//Locator of the Text Area 
	@FindBy (xpath = "//textarea[@name='comment']") private WebElement txtarea;
	
	//Locator of the CheckBox Terms&COndition
	@FindBy (xpath = "//label[@class='checkbox']//input[@name='agree']") public WebElement chckboxtermscondition;
	
	
	public void clickContinueBillingDetBtn() throws InterruptedException {
		 this.payaddcontbtn.click();
	}
	
	public void clickContinueShipngAddBtn() throws InterruptedException {
		 this.shipngaddbconttn.click();
	}
	
	public void clickContinueShipngMethodBtn() throws InterruptedException {
		 this.shipngmethdcontbtn.click();
	}
	
	public void clickContinuePayMethodBtn() throws InterruptedException {
		 this.pymntmethodcontbtn.click();
	}
	
	public void clickConfirmOrderBtn() throws InterruptedException {
		 this.confirmorderbtn.click();
	}
	
	public void addText(String textmsg) {
		this.txtarea.sendKeys(textmsg);
	}
	
	
}
