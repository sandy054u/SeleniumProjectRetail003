package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderConfirmationPagePOM {

	private WebDriver driver;

	public OrderConfirmationPagePOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Locator of the Order confirmation Success Message
@FindBy (xpath = "//div[@class='tb_text_wrap tb_sep']//p[contains(text(),'Your order has been successfully processed!')]") public WebElement orderconfirmSuccessmsg;


}