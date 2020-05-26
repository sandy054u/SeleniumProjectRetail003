package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class UserRegistrationPOM {

	private WebDriver driver;

	public UserRegistrationPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "login")
	private WebElement userName;
		@FindBy(id = "password")
	private WebElement password;
	@FindBy(id = "formLogin_submitAuth")
	private WebElement loginBtn;

	
	@FindBy(name = "firstname")	private WebElement fname;
	@FindBy(name = "lastname")	private WebElement lname;
	@FindBy(name = "email") 	private WebElement email;
	@FindBy(name = "telephone") private WebElement telep;
	@FindBy(name = "address_1") private WebElement add1;
	@FindBy(name = "address_2") private WebElement add2;
	
	@FindBy(name = "city")	private WebElement city;
	@FindBy(name = "postcode") private WebElement pcode;
	@FindBy(name = "country_id") private WebElement country;
	@FindBy(name = "zone_id") private WebElement zone;
	
	@FindBy(name = "password") private WebElement pswd;
	@FindBy(name = "confirm") private WebElement cnfmpswd;
	
	@FindBy(xpath = "//label[contains(text(),'Yes')]") private WebElement newsletter1;
//	By opt2 = By.xpath("//label[contains(text(),'No')]");
	@FindBy(xpath = "//label[contains(text(),'No')]") private WebElement newsletter2;
	
	@FindBy(name = "agree") private WebElement agree;
	
	@FindBy(xpath = "//input[@class='btn btn-primary']") private WebElement contbtn;
	
	public void enterFirstName(String fnm) {
		this.fname.clear();
		this.fname.sendKeys(fnm);
	}
	
	public void enterLastname(String lnm) {
		this.lname.clear();
		this.lname.sendKeys(lnm);
	}
	
	public void enterEmail(String eml) {
		this.email.clear();
		this.email.sendKeys(eml);
	}
	
	public void enterTelep(String tel) {
		this.telep.clear();
		this.telep.sendKeys(tel);
	}
	
	public void enterAddress1(String add1) {
		this.add1.clear();
		this.add1.sendKeys(add1);
	}
	
	public void enterAddress2(String add2) {
		this.add2.clear();
		this.add2.sendKeys(add2);
	}
	
	public void enterCity(String city) {
		this.city.clear();
		this.city.sendKeys(city);
	}
	
	public void enterPostCode(String pcode) {
		this.pcode.clear();
		this.pcode.sendKeys(pcode);
	}
	
	
	public void selectCountry(String cnt) {
		
		Select drpCountry = new Select(country);
		drpCountry.selectByVisibleText(cnt);
		
	}
	
	public void selectState(String st) {
		
		Select drpCountry = new Select(zone);
		drpCountry.selectByVisibleText(st);
		
	}
	
	public void enterPassword(String pswd) {
		this.pswd.clear();
		this.pswd.sendKeys(pswd);
	}
	
	public void enterConfirmPswd(String confpswd) {
		this.cnfmpswd.clear();
		this.cnfmpswd.sendKeys(confpswd);
	}
	
	public void selectNewsLetter(String sel) {
		if(sel.equalsIgnoreCase("Yes"))
		{
		this.newsletter1.click();
		}
		else
		{
			this.newsletter2.click();
		}
	}
	
	public void selectPrivacyPolicy() {
		this.agree.click();
	}
	
	public void clickContinueButton() {
		System.out.println("About to click on the Continue method");
		this.contbtn.click();
	}
	
	}

