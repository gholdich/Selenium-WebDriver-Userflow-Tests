package junit.userflows.pages.ChangingPassword;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
	
	private WebDriver driver;
	
	private static final String user = "selenium";
	private static final String password = "selenium321";
	
	@FindBy(name = "user")
	WebElement inputUser;
	
	@FindBy(name = "pass")
	WebElement inputPass;
	
	@FindBy(css = "input[type=\'submit\']")
	WebElement loginButton;
	
	public Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void performLogin() {
	    inputUser.clear();
	    ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '"+user+"');", inputUser);
	    inputPass.clear();
	    ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '"+password+"');", inputPass);
	    loginButton.click();
	}
	
	public String getUser() {
		return user;
	}
	
	public String getPass() {
		return password;
	}

	public String getURL() {
		return Constants.getBaseUrl()+"login/";
	}
}
