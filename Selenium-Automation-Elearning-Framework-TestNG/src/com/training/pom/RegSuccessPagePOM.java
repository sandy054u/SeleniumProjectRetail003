package com.training.pom;
 

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class RegSuccessPagePOM {

	private WebDriver driver;

	public RegSuccessPagePOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
 
	 @FindBy(xpath = "//div[@class='tb_text_wrap tb_sep']//p[contains(text(),'Congratulations! Your new account has been success')]") public WebElement regsuccessmsg;
	
	// @FindBy(className = "tb_text_wrap tb_sep") public WebElement regsuccessmsg;
	@FindBy(xpath = "//a[@class='btn btn-primary']") private WebElement contbtn1;
	
	//* checking the successful message.
	public String checkSuccessMsg() {
		System.out.print("Inside the checkSuccessMsg POM page");
		String str = this.regsuccessmsg.getText();
		System.out.println("Fetched the message: " + str);
//		Assert.assertEquals(msg, str);
		return(str);
	}
	
	//* checking the continue button in the success page.
	public void checkContinueBtn(){
//		boolean view = drv.findElement(By.xpath("//*[@id='hplogo']")).isDisplayed();
//		Assert.assertTrue(view);
		this.contbtn1.click();
		}
}