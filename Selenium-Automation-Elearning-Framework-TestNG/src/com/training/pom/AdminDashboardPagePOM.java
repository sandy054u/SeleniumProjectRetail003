package com.training.pom;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;

public class AdminDashboardPagePOM {
	private WebDriver driver; 
	
	public AdminDashboardPagePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(id = "button-menu") private WebElement admdashboardMenuBtn;
	@FindBy(xpath = "//li[@id='menu-catalog']") private WebElement admcataloglnk;
	@FindBy(xpath = "//ul[@class='collapse in']//a[contains(text(),'Products')]") private WebElement admprodlnk;
	@FindBy(xpath = "//div[@class='pull-right']//a[@class='btn btn-primary']") private WebElement addprodicon; 
	@FindBy(xpath = "//h3[@class='panel-title']") private WebElement addprodlbl;
	@FindBy(id = "input-name1") private WebElement prodname;
	@FindBy(id = "input-meta-title1") private WebElement metatagtitle;
	@FindBy(id = "input-meta-description1") private WebElement metatagdesc; 
	
	//Locator for the Data Tab in the Admin page
	@FindBy(xpath = "//a[contains(text(),'Data')]") public WebElement datatab;
	@FindBy(id ="input-model") private WebElement model;
	@FindBy(id ="input-price") private WebElement price;
	@FindBy(id ="input-quantity") private WebElement qty;
	
	//Locator for the Link tab in the Admin page
	@FindBy(xpath = "//a[contains(text(),'Links')]") private WebElement linkstab;
	@FindBy(id = "input-manufacturer") private WebElement manufacture;
	@FindBy(id = "input-category") private WebElement categorylist;
	
	//locator of the Success message after adding the product
	@FindBy(xpath = "//div[@class='alert alert-success'][contains(text(),' Success')]") private WebElement successmsg;
	
	//Locator of the Logout 
	@FindBy(xpath = "//span[contains(text(),'Logout')]") private WebElement Logout;
	
	//Locator of the Save button in the Admin page
	@FindBy(xpath = "//div[@class='pull-right']//button[@class='btn btn-primary']") private WebElement savebtn;
	public void clickDashboardMenu() {
		this.admdashboardMenuBtn.click();
	}

	public void clickCatalogue() {
		this.admcataloglnk.click();
	}

	public void clickProduct() {
		this.admprodlnk.click();
	}
	
	public void clickAddProdIcon() {
		this.addprodicon.click();
	}
	
	public String verifyAddProdLabel() {
		String lbl = addprodlbl.getText();
		return (lbl);
	}
	
	public void enterProdName(String prodnm) {
		this.prodname.clear();
		this.prodname.sendKeys(prodnm);
	}
	
	public void enterMetaTagTitle(String metatagttl) {
	this.metatagtitle.clear();
	this.metatagtitle.sendKeys(metatagttl);
	}
	
	public void enterMetaTagDesc(String metatagdsc) {
		this.metatagdesc.clear();
		this.metatagdesc.sendKeys(metatagdsc);
		}
	
	public void enterModel(String model) {
		this.model.clear();
		this.model.sendKeys(model);
		}
	
	public void enterPrice(int price) {
		this.model.clear();
		this.model.sendKeys(String.valueOf(price));
		}
	public void enterQuantity(int qty) {
		this.model.clear();
		String qty1 = String.valueOf(qty);
		this.model.sendKeys(qty1);
		}
	public void clickLinks() {
		this.linkstab.click();
	}
	public void enterCategory(String cat) { 
		this.categorylist.sendKeys(cat);
			
		}
		
	public void clickSaveBtn() {
		this.savebtn.click();
	}
	
	public String verifySuccessMsg() {
		String msg = successmsg.getText();
		return(msg);
	}
	
	public void clickLogOut() {
		this.Logout.click();
	}
	}
