package junit.userflows.pages.ChangingPassword;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {
	
	@SuppressWarnings("unused")
	private WebDriver driver;
	
	@FindBy(css = ".navbar ul li:last-child a.dropdown-toggle")
	WebElement userDropdownMenu;
	
	@FindBy(linkText = "Log In")
	WebElement menuItem_login;
	
	@FindBy(linkText = "User CP")
	WebElement menuItem_userCP;
	
	public Home(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickUserDropdown() {
		userDropdownMenu.click();
	}
	
	public void clickLogin() {
		menuItem_login.click();
	}
	
	public void clickUserCP() {
		menuItem_userCP.click();
	}
	
	public String getSessionUser() {
		return userDropdownMenu.getText();
	}

	public String getURL() {
		return Constants.getBaseUrl();
	}
}
