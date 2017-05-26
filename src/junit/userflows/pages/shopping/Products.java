package junit.userflows.pages.shopping;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.PageFactory;

public class Products {
	private WebDriver driver;
	
	@FindAll({@FindBy(css = "ul.product_list.grid > li .product-container .button-container .lnk_view")})
	List <WebElement> product;
	
	
	public Products(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickViewProductDetailed(WebElement product) {
		driver.get(product.getAttribute("href"));
	}
	
	public WebElement getProduct(int itemNumber) {
		return product.get((itemNumber-1));
	}
	
	public String getURL() {
		return Constants.getBaseUrl()+"index.php?id_category=3&controller=category";
	}

}
