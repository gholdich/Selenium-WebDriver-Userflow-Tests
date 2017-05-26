package junit.userflows.pages.shopping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {
	private WebDriver driver;
	
	@FindBy(linkText = "Women")
	WebElement productCatorgory;
	
	public Home(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickProductsPage() {
		driver.get(productCatorgory.getAttribute("href"));
	}
	
	public String getURL() {
		return Constants.getBaseUrl()+"index.php";
	}
	
	
	
}
