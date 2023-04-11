package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	//elements
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement lnkMyaccount;
	
	@FindBy(xpath = "//a[@class='dropdown-item'][normalize-space()='Register'")
	WebElement lnkRegister;
	
	//action methods
	public void clickMyAccount() {
		lnkMyaccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
}
