package com.training.pom;
 

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class AccountpagePOM {

	private WebDriver driver;

	public AccountpagePOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
 
@FindBy (xpath = "//span[@class='tb_icon']/i[@class='fa fa-user-o']") public WebElement acccicon;

@FindBy (xpath = "//span[contains(text(),'LOGOUT')]") public WebElement logout;

}