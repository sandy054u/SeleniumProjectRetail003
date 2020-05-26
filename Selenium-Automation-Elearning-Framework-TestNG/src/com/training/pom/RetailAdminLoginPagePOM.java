package com.training.pom;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import junit.framework.Assert;

public class RetailAdminLoginPagePOM {
	private WebDriver driver; 
	
	public RetailAdminLoginPagePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(id = "input-username") 	private WebElement admusername; 
	@FindBy(id="input-password") private WebElement admpswd;
	@FindBy(xpath = "//div[@class = \"text-right\"]//button[@class = 'btn btn-primary']") private WebElement admloginBtn;
	
	public void enterAdmUserID(String admid) {
		this.admusername.clear();
		this.admusername.sendKeys(admid); 
	}
	
	public void enterAdmPassword(String admpswd) {
		this.admpswd.clear(); 
		this.admpswd.sendKeys(admpswd); 
	}
	
	//Click on the Login Button
	public void clickAdmLoginBtn() {
		System.out.println("inside the clickLoginbutton method");
		this.admloginBtn.click();; 
		
	}
}