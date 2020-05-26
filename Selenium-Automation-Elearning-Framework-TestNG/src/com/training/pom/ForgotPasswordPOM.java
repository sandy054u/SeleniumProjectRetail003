package com.training.pom;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPOM {
	private WebDriver driver; 
	
	public ForgotPasswordPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(xpath = "//input[@class='btn btn-primary']") 	private WebElement LoginBtn; 
	
	@FindBy(id = "input-email") 	private WebElement forgotemail; 
	@FindBy(xpath ="//input[@class='btn btn-primary']") 	private WebElement forgotpswdContBtn;
	
	 
	public void enterRecoveryEmail(String email) {
		this.forgotemail.clear();
		this.forgotemail.sendKeys(email); 
	}
	
	public void clickForgotContinueBtn() {
		this.forgotpswdContBtn.click();
	}
	
}