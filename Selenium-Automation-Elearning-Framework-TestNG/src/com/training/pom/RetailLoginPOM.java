package com.training.pom;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import junit.framework.Assert;

public class RetailLoginPOM {
	private WebDriver driver; 
	
	public RetailLoginPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(xpath = "//input[@class='btn btn-primary']") 	private WebElement LoginBtn; 
	
	@FindBy(id = "input-email") 	private WebElement email; 
	@FindBy(id="input-password") 	private WebElement password;
	
	//* Locator -  Register link in the Login page.
	@FindBy(linkText = "Register") private WebElement lnkreg;
	
	//*Forgotten Password Link in the Login page
	@FindBy(xpath = "//a[contains(text(),'Forgotten Password')]") private WebElement forgotPswdlnk;
	
	//*Invalid login messasge
	@FindBy(xpath= "//div[@class='alert alert-danger' and contains(text(),' Warning: No match for E-Mail Address and/or Password.')]") private WebElement invalidLoginmsg;
	
	//*Forgotten email message after providing the email id and clicking on the Continue button
	
	@FindBy(xpath = "//div[contains(text(),' An email with a confirmation link has been sent your email address.')]") public WebElement fogotemailmsg; 
	
	public void enterEmail(String email) {
		this.email.clear();
		this.email.sendKeys(email); 
	}
	
	public void enterPassword(String pswd) {
		this.password.clear(); 
		this.password.sendKeys(pswd); 
	}
	
	//Click on the Login Button
	public void clickLoginBtn() {
		System.out.println("inside the clickLoginbutton method");
		this.LoginBtn.click(); 
		
	}
	
	public String loginFailureMessage() {
		String  actloginmsg = this.invalidLoginmsg.getText();
		
		return (actloginmsg);
	}	
	
	//CLick on the Register button
	public void clickRegisterBtn() {
		this.lnkreg.click();
	}
	
	public void clickForgottenPswdLnk() {
		System.out.println("inside the Forgotten password method");
		this.forgotPswdlnk.click();
	}
	
}

 
