package junit.userflows.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import junit.userflows.pages.shopping.Cart;
import junit.userflows.pages.shopping.Constants;
import junit.userflows.pages.shopping.Home;
import junit.userflows.pages.shopping.Products;
import junit.userflows.pages.shopping.ViewProduct;

public class ShoppingTests {
	static WebDriver driver;
	ExtentReports report;
	ExtentTest logger;
	Home home;
	Products products;
	ViewProduct viewProduct;
	Cart cart;

	@Parameters("browser")
	@BeforeClass
	public static void setUpBeforeClass(String browser) {
		if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Selenium\\geckodriver.exe");
		    FirefoxProfile profile = new FirefoxProfile();
		    profile.setPreference("browser.tabs.remote.autostart", false);
		    profile.setPreference("browser.tabs.remote.autostart.1", false);
		    profile.setPreference("browser.tabs.remote.autostart.2", false);                                                         
		    profile.setPreference("browser.tabs.remote.force-enable", "false");
			driver = new FirefoxDriver(profile);
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
			driver = new ChromeDriver();
		}
	}
	
	@Parameters("browser")
	@BeforeTest
	public void setUp(String browser) {
		if(browser.equalsIgnoreCase("firefox"))
		report = new ExtentReports("test-reports\\extentReport_shopping_firefox.html");
		else if (browser.equalsIgnoreCase("chrome"))
		report = new ExtentReports("test-reports\\extentReport_shopping_chrome.html");
	}
	
	@Test(priority = 1, enabled = true)
	public void testShoppingUserFlow_loadingWebsite_navigateToHomepage() {
		logger = report.startTest("Shopping User Flow");
		logger.log(LogStatus.INFO, "Browser started.");
		logger.log(LogStatus.INFO, "Navigating to homepage...");
		driver.get(Constants.getBaseUrl());
		home = new Home(driver);
		logger.log(LogStatus.INFO, "Verifying Homepage has loaded...");
		assertTrue(isPageLoaded(5));
		logger.log(LogStatus.PASS, "Verified homepage has loaded.");
		assertEquals(home.getURL(), driver.getCurrentUrl());
		logger.log(LogStatus.PASS, "Verified homepage URL.");
	}
	
	@Test(priority = 2, enabled = true)	
	public void testShoppingUserFlow_interactingWithDropdown_navigateToProductCatorgoryPage() {
		home.clickProductsPage();
		logger.log(LogStatus.INFO, "Clicked menu item(Women), redirecting to product catorgory page.");
		products = new Products(driver);
		logger.log(LogStatus.INFO, "Verifying product catorgory page has loaded...");
		assertTrue(isPageLoaded(5));
		logger.log(LogStatus.PASS, "Verified product catorgory page has loaded.");
		assertEquals(products.getURL(), driver.getCurrentUrl());
		logger.log(LogStatus.PASS, "Verified product catorgory page URL.");
	}
	
	@Test(priority = 3, enabled = true)
	public void testShoppingUserFlow_viewingProductInDetail_productOne() {		
		products.clickViewProductDetailed(products.getProduct(1));
		logger.log(LogStatus.INFO, "Clicked product item(1), redirecting to product page.");
		viewProduct = new ViewProduct(driver);
		logger.log(LogStatus.INFO, "Verifying product page has loaded...");
		assertTrue(isPageLoaded(5));
		logger.log(LogStatus.PASS, "Verified product page has loaded.");
		assertEquals(viewProduct.getURL(1), driver.getCurrentUrl());
		logger.log(LogStatus.PASS, "Verified product page URL.");
	}
		
	@Test(priority = 4, enabled = true)
	public void testShoppingUserFlow_returnToProductCatorgory_fromProductOne() {	
		viewProduct.clickReturnToProducts();
		logger.log(LogStatus.INFO, "Clicked product catorgory, returning to product catorgory page.");
		logger.log(LogStatus.INFO, "Verifying product catorgory page has loaded...");
		assertTrue(isPageLoaded(5));
		logger.log(LogStatus.PASS, "Verified product catorgory page has loaded.");
		assertEquals(products.getURL(), driver.getCurrentUrl());
		logger.log(LogStatus.PASS, "Verified product catorgory page URL.");
	}
	
	@Test(priority = 5, enabled = true)
	public void testShoppingUserFlow_viewingProductInDetail_productThree() {
		products.clickViewProductDetailed(products.getProduct(3));
		logger.log(LogStatus.INFO, "Clicked product item(3), redirecting to product page.");
		logger.log(LogStatus.INFO, "Verifying product page has loaded...");
		assertTrue(isPageLoaded(5));
		logger.log(LogStatus.PASS, "Verified product page has loaded.");
		assertEquals(viewProduct.getURL(3), driver.getCurrentUrl());
		logger.log(LogStatus.PASS, "Verified product page URL.");
	}
	
	@Test(priority = 6, enabled = true)
	public void testShoppingUserFlow_returnToProductCatorgory_fromProductThree() {	
		viewProduct.clickReturnToProducts();
		logger.log(LogStatus.INFO, "Clicked product catorgory, returning to product catorgory page.");
		logger.log(LogStatus.INFO, "Verifying product catorgory page has loaded...");
		assertTrue(isPageLoaded(5));
		logger.log(LogStatus.PASS, "Verified product catorgory page has loaded.");
		assertEquals(products.getURL(), driver.getCurrentUrl());
		logger.log(LogStatus.PASS, "Verified product catorgory page URL.");
	}
	
	@Test(priority = 7, enabled = true)
	public void testShoppingUserFlow_viewingProductInDetail_productTwo() {
		products.clickViewProductDetailed(products.getProduct(2));
		logger.log(LogStatus.INFO, "Clicked product item(2), redirecting to product page.");
		logger.log(LogStatus.INFO, "Verifying product page has loaded...");
		assertTrue(isPageLoaded(5));
		logger.log(LogStatus.PASS, "Verified product page has loaded.");
		assertEquals(viewProduct.getURL(2), driver.getCurrentUrl());
		logger.log(LogStatus.PASS, "Verified product page URL.");
	}
	
	@Test(priority = 8, enabled = true)
	public void testShoppingUserFlow_addToCart_productTwo() {
		logger.log(LogStatus.INFO, "Adding product to cart...");
		viewProduct.addToCart();
		logger.log(LogStatus.INFO, "Verifying product has been added to cart...");
		waitForElementVisible(viewProduct.getAddedToCartMessage(), 5);
		assertEquals("Product successfully added to your shopping cart", viewProduct.getAddedToCartMessage().getText());
		logger.log(LogStatus.PASS, "Verified product has been successfuly added to cart.");
	}
		
	@Test(priority = 9, enabled = true)
	public void testShoppingUserFlow_proceedToCheckout_verifyProductTwoHasBeenAdded() {
		logger.log(LogStatus.INFO, "Proceeding to checkout...");
		viewProduct.proceedToCheckout();
		cart = new Cart(driver);
		logger.log(LogStatus.INFO, "Verifying checkout page has loaded...");
		assertTrue(isPageLoaded(5));
		assertEquals("SHOPPING-CART SUMMARY\nYour shopping cart contains: 1 Product", cart.getCartTitle());
		logger.log(LogStatus.PASS, "Verified checkout page has loaded.");
		assertEquals(cart.getURL(), driver.getCurrentUrl());
		logger.log(LogStatus.PASS, "Verified checkout page URL.");
	}
		
	@Test(priority = 10, enabled = true)
	public void testShoppingUserFlow_proceedThroughCheckout_nextStage_authentication() {
		logger.log(LogStatus.INFO, "Proceeding to next stage (Authentication)...");
		cart.proceedThroughCheckout(cart.getCheckoutButton_Summary());
		delay();
		logger.log(LogStatus.INFO, "Verifying next checkout stage has loaded...");
		assertTrue(isPageLoaded(5));
		assertEquals("AUTHENTICATION", cart.getCartTitle());
		logger.log(LogStatus.PASS, "Verified next checkout stage has loaded.");
	}
	
	@Test(priority = 11, enabled = true)
	public void testShoppingUserFlow_proceedThroughCheckout_performLogin_processToNextStage_addresses() {
		logger.log(LogStatus.INFO, "Performing login...");
		cart.performLogin();
		logger.log(LogStatus.INFO, "Proceeding to next stage (Addresses)...");
		delay();
		logger.log(LogStatus.INFO, "Verifying next checkout stage has loaded...");
		assertTrue(isPageLoaded(5));
		assertEquals("ADDRESSES", cart.getCartTitle());
		logger.log(LogStatus.PASS, "Verified next checkout stage has loaded.");
		logger.log(LogStatus.PASS, "Verified login was successful.");
	}
	
	@Test(priority = 12, enabled = true)
	public void testShoppingUserFlow_proceedThroughCheckout_addComment_proceedToNextStage_shipping() {
		logger.log(LogStatus.INFO, "Adding comment to address comments...");
		cart.addOrderComment();
		logger.log(LogStatus.INFO, "Proceeding to next stage (Shipping)...");
		cart.proceedThroughCheckout(cart.getCheckoutButton_Address());
		delay();
		logger.log(LogStatus.INFO, "Verifying next checkout stage has loaded...");
		assertTrue(isPageLoaded(5));
		assertEquals("SHIPPING", cart.getCartTitle());
		logger.log(LogStatus.PASS, "Verified next checkout stage has loaded.");
	}
	
	@Test(priority = 13, enabled = true)
	public void testShoppingUserFlow_proceedThroughCheckout_acceptTerms_proceedToNextStage_payment() {
		logger.log(LogStatus.INFO, "Accepting terms and conditions (Tick Box)...");
		cart.acceptTerms();
		logger.log(LogStatus.INFO, "Proceeding to next stage (Payment)...");
		cart.proceedThroughCheckout(cart.getCheckoutButton_Shipping());
		delay();
		logger.log(LogStatus.INFO, "Verifying next checkout stage has loaded...");
		assertTrue(isPageLoaded(5));
		assertEquals("PLEASE CHOOSE YOUR PAYMENT METHOD", cart.getCartTitle());
		logger.log(LogStatus.PASS, "Verified next checkout stage has loaded.");
	}
	
	@Test(priority = 14, enabled = true)
	public void testShoppingUserFlow_proceedThroughCheckout_selectPayment_proceedToFinalStage() {
		logger.log(LogStatus.INFO, "Selecting payment option...");
		cart.clickPaymentOption(cart.getWireTransferOption());
		logger.log(LogStatus.INFO, "Proceeding to final stage (Confirm Order)");
		delay();
		logger.log(LogStatus.INFO, "Verifying final checkout stage has loaded...");
		assertTrue(isPageLoaded(5));
		assertEquals("ORDER SUMMARY", cart.getCartTitle());
		logger.log(LogStatus.PASS, "Verified final checkout stage has loaded.");
	}
		
	@Test(priority = 15, enabled = true)
	public void testShoppingUserFlow_processCheckout_verifyOrderHasBeenSuccessful() {
		logger.log(LogStatus.INFO, "Confirming order...");
		cart.proceedThroughCheckout(cart.getConfirmOrderButton());
		logger.log(LogStatus.INFO, "Processing checkout...");
		delay();
		logger.log(LogStatus.INFO, "Verifying order confirmation page has loaded...");
		assertTrue(isPageLoaded(5));
		assertEquals("ORDER CONFIRMATION", cart.getCartTitle());
		logger.log(LogStatus.PASS, "Verified order confirmation page has loaded.");
		assertTrue(cart.isOrderSuccessful());
		logger.log(LogStatus.PASS, "Verified order was successful.");
	}
	
	public boolean isPageLoaded(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until((ExpectedCondition<Boolean>) w ->
        ((JavascriptExecutor) w).executeScript("return document.readyState").equals("complete"));
	}
	
	public void waitForElementVisible(WebElement element, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void delay() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@AfterTest
	public void tearDown() {
		report.endTest(logger);
		report.flush();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		driver.quit();
	}

}
