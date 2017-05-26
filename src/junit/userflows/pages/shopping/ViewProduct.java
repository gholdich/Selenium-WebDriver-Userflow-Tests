package junit.userflows.pages.shopping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewProduct {
	private WebDriver driver;
	
	@FindBy(css = "div.breadcrumb.clearfix > a[title=\"Women\"]")
	WebElement returnToCatorgory;
	
	@FindBy(name = "Submit")
	WebElement submitForm;
	
	@FindBy(xpath = "//div[@id='layer_cart']/div/div[2]/div[4]/a")
	WebElement quickViewCheckout;
	
	@FindBy(xpath = "//*[@id='layer_cart']/div[1]/div[1]/h2")
	WebElement addedToCartMessage;
	
	public ViewProduct(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickReturnToProducts() {
		driver.get(returnToCatorgory.getAttribute("href"));
	}
	
	public void addToCart() {
		submitForm.click();
	}
	
	public WebElement getAddedToCartMessage() {
		return addedToCartMessage;
	}
	
	public void proceedToCheckout() {
		driver.get(quickViewCheckout.getAttribute("href"));
	}
	
	public String getURL(int productId) {
		return Constants.getBaseUrl()+"index.php?id_product="+(productId)+"&controller=product";
	}

}
