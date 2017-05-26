package junit.userflows.pages.shopping;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cart {
	private WebDriver driver;
	private static final String email = "abc@xyz.co.uk";
	private static final String password = "test123";
	private static final String comment = "This is an Automated Test.";
	private static final String orderConfimation = "Your order on My Store is complete.";
	
	@FindBy(css = "#center_column h1")
	WebElement cartTitle;
	
	@FindBy(xpath = "//div[@id='center_column']/p[2]/a")
	WebElement checkoutButton_Summary;
	
	@FindBy(id = "email")
	WebElement inputEmail;
	
	@FindBy(id = "passwd")
	WebElement inputPass;
	
	@FindBy(id = "SubmitLogin")
	WebElement submitLogin;
	
	@FindBy(name = "message")
	WebElement orderComment;
	
	@FindBy(name = "processAddress")
	WebElement checkoutButton_Address;
	
	@FindBy(id = "cgv")
	WebElement radioButton_terms;
	
	@FindBy(name = "processCarrier")
	WebElement checkoutButton_Shipping;
	
	@FindBy(linkText = "Pay by bank wire (order processing will be longer)")
	WebElement wireTransfer;
	
	@FindBy(xpath = "(//button[@type='submit'])[2]")
	WebElement confirmOrderButton;
	
	@FindBy(css = "p.cheque-indent > strong.dark")
	WebElement orderSuccess;
	
	public Cart(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void proceedThroughCheckout(WebElement proceedButton) {
		proceedButton.click();
	}
	
	public void clickPaymentOption(WebElement paymentType) {
		driver.get(paymentType.getAttribute("href"));
	}

	public void performLogin() {
	    inputEmail.clear();
	    ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '"+email+"');", inputEmail);
	    inputPass.clear();
	    ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '"+password+"');", inputPass);
	    submitLogin.click();
	}
	
	public void addOrderComment() {
	    orderComment.clear();
	    ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '"+comment+"');", orderComment);
	}
	
	public void acceptTerms() {
		radioButton_terms.click();
	}
	
	public boolean isOrderSuccessful() {
		return orderSuccess.getText().equals(orderConfimation);
	}
	
	public String getURL() {
		return Constants.getBaseUrl()+"index.php?controller=order";
	}
	
	public String getCartTitle() {
		return cartTitle.getText();
	}
	
	public WebElement getCart() {
		return cartTitle;
	}
	
	public WebElement getCheckoutButton_Summary() {
		return checkoutButton_Summary;
	}

	public WebElement getCheckoutButton_Address() {
		return checkoutButton_Address;
	}

	public WebElement getCheckoutButton_Shipping() {
		return checkoutButton_Shipping;
	}

	public WebElement getConfirmOrderButton() {
		return confirmOrderButton;
	}
	
	public WebElement getWireTransferOption() {
		return wireTransfer;
	}
}
