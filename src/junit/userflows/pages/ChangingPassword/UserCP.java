package junit.userflows.pages.ChangingPassword;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserCP {
	private static final String newPassword = "selenium321";
	
	private WebDriver driver;
	
	@FindBy(name = "curpass")
	WebElement inputCurPass;
	
	@FindBy(name = "newpass")
	WebElement inputNewPass;
	
	@FindBy(name = "conf_newpass")
	WebElement inputNewPass_confirm;
	
	@FindBy(css = "input[type=\'submit\']")
	WebElement submitChanges;
	
	@FindBy(css = "p")
	WebElement changedPassword_confirmation;
	
	@FindBy(linkText = "Log Out")
	WebElement menuItem_logout;
	
	public UserCP(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterCurPass(String currrentPassword) {
		inputCurPass.clear();
	    ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '"+currrentPassword+"');", inputCurPass);
	}
	
	public void enterNewPass() {
		inputNewPass.clear();
	    ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '"+newPassword+"');", inputNewPass);
	}
	
	public void enterNewPassConfirmation() {
		inputNewPass_confirm.clear();
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '"+newPassword+"');", inputNewPass_confirm);
	}
	
	public void ChangePassword() {
		submitChanges.click();
	}
	
	public String getSuccessMessage() {
		return changedPassword_confirmation.getText();
	}
	
	public void clickLogout() {
		menuItem_logout.click();
	}
	
	public String getURL() {
		return Constants.getBaseUrl()+"usercp";
	}
	
}
