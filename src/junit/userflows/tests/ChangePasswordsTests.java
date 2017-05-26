package junit.userflows.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.testng.annotations.AfterTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import junit.userflows.pages.ChangingPassword.Constants;
import junit.userflows.pages.ChangingPassword.Home;
import junit.userflows.pages.ChangingPassword.Login;
import junit.userflows.pages.ChangingPassword.UserCP;

public class ChangePasswordsTests {
	static WebDriver driver;
	ExtentReports report;
	ExtentTest logger;
	Home home;
	Login login;
	UserCP userCP;

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
		report = new ExtentReports("test-reports\\extentReport_changingPassword_firefox.html");
		else if (browser.equalsIgnoreCase("chrome"))
		report = new ExtentReports("test-reports\\extentReport_changingPassword_chrome.html");
	}

	@Test(priority = 1, enabled = true)
	public void testChangingPasswordUserFlow_loadingWebsite_navigateToHomepage() {
		logger = report.startTest("Changing Password User Flow");
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
	public void testChangingPasswordUserFlow_interactingWithDropdown_navigateToLoginPage() {
		logger.log(LogStatus.INFO, "Interacting with dropdown menu(User)...");
		home.clickUserDropdown();
		home.clickLogin();
		logger.log(LogStatus.INFO, "Clicked menu item(Login), redirecting to login page.");
		login = new Login(driver);
		delay();
		logger.log(LogStatus.INFO, "Verifying login page has loaded...");
		assertTrue(isPageLoaded(5));
		logger.log(LogStatus.PASS, "Verified login page has loaded.");
		assertEquals(login.getURL(), driver.getCurrentUrl());
		logger.log(LogStatus.PASS, "Verified login page URL.");
	}
		
	@Test(priority = 3, enabled = true)
	public void testChangingPasswordUserFlow_performingLogin_redirectionOnLogin() {
		logger.log(LogStatus.INFO, "Performing login...");
		logger.log(LogStatus.INFO, "Entering suppiled credientials...");
		login.performLogin();
		logger.log(LogStatus.INFO, "Logging in...");
		logger.log(LogStatus.INFO, "Logged in, redirecting to homepage.");
		delay();
		logger.log(LogStatus.INFO, "Verifying homepage has loaded...");
		assertTrue(isPageLoaded(5));
		logger.log(LogStatus.PASS, "Verified homepage has loaded.");
		assertEquals(home.getURL(), driver.getCurrentUrl());
		logger.log(LogStatus.PASS, "Verified homepage URL.");
		assertEquals(login.getUser(), home.getSessionUser());
		logger.log(LogStatus.PASS, "Verified login has been successful.");
	}
	
	@Test(priority = 4, enabled = true)
	public void testChangingPasswordUserFlow_interactingWithDropdown_navigateToControlPanel() {		
		logger.log(LogStatus.INFO, "Interacting with dropdown menu(User)...");
		home.clickUserDropdown();
		home.clickUserCP();
		logger.log(LogStatus.INFO, "Clicked menu item(UserCP), redirecting to user control panel page.");
		userCP = new UserCP(driver);
		delay();
		logger.log(LogStatus.INFO, "Verifying user control panel page has loaded...");
		assertTrue(isPageLoaded(5));
		logger.log(LogStatus.PASS, "Verified user control panel page has loaded.");
		assertEquals(userCP.getURL(), driver.getCurrentUrl());
		logger.log(LogStatus.PASS, "Verified user control panel page URL.");
	}
	
	@Test(priority = 5, enabled = true)
	public void testChangingPasswordUserFlow_performingPasswordChange_inputCurrentAndNewAndconfirmingNew() {
		logger.log(LogStatus.INFO, "Entering current password...");
		userCP.enterCurPass(login.getPass());
		logger.log(LogStatus.INFO, "Entering new password...");
		userCP.enterNewPass();
		logger.log(LogStatus.INFO, "Confirming new password...");
		userCP.enterNewPassConfirmation();
		logger.log(LogStatus.INFO, "Submiting password change...");
		userCP.ChangePassword();
		logger.log(LogStatus.INFO, "Password changed, redirecting to action status page.");
		delay();
		logger.log(LogStatus.INFO, "Verifying action status page has loaded...");
		assertTrue(isPageLoaded(5));
		logger.log(LogStatus.PASS, "Verified action status page has loaded.");
		assertEquals(userCP.getURL()+"?id=2", driver.getCurrentUrl());
		logger.log(LogStatus.PASS, "Verified action status page URL.");
		assertEquals(login.getUser()+", your account has been successfully updated. Return to Home.", userCP.getSuccessMessage());
		logger.log(LogStatus.PASS, "Verified Password Change has been successful.");
	}
	
	@Test(priority = 6, enabled = true)
	public void testChangingPasswordUserFlow_interactingWithDropdown_loggingOut_verifyRedirection() {			
		logger.log(LogStatus.INFO, "Interacting with dropdown menu(User)...");
		home.clickUserDropdown();
		userCP.clickLogout();
		logger.log(LogStatus.INFO, "Clicked menu item(Log Out), redirecting to homepage.");
		delay();
		logger.log(LogStatus.INFO, "Verifying Homepage has loaded...");
		assertTrue(isPageLoaded(5));
		logger.log(LogStatus.PASS, "Verified homepage has loaded.");
		assertEquals(home.getURL()+"index", driver.getCurrentUrl());
		logger.log(LogStatus.PASS, "Verified homepage URL.");
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
