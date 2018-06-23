package asf.asf.general;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class Selenium {
	//public static RemoteWebDriver driver;
	public static WebDriver driver;
	private static int waitTime = 20;

	@SuppressWarnings("deprecation")
	public static void startRemoteDriver(String browser) {
		try {
			String webDriverHubUrl = "http://localhost:4444/wd/hub";//System.getProperty("grid");

			DesiredCapabilities c = new DesiredCapabilities();
			c.setCapability("platform", Platform.WIN10);

			if (browser.toLowerCase().contentEquals("chrome")) {
				System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
				c = DesiredCapabilities.chrome();
				c.setBrowserName("chrome");
			} else if (browser.toLowerCase().contentEquals("ie") || browser.toLowerCase().contentEquals("internet explorer")) {
				c = DesiredCapabilities.internetExplorer();
				c.setBrowserName("ie");
				c.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);  //If IE fail to work, please remove this and remove enable protected mode for all the 4 zones from Internet options
			} else if (browser.toLowerCase().contentEquals("firefox")) {
				//System.setProperty("webdriver.gecko.driver","C:\tech2\selenium\geckodriver.exe");
				c = DesiredCapabilities.firefox();
				c.setBrowserName("firefox");
			} else if (browser.toLowerCase().contentEquals("opera")) {
				c = DesiredCapabilities.opera();
				c.setBrowserName("opera");
			}

			driver = new RemoteWebDriver(new URL(webDriverHubUrl), c);
			
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Parameters({"browser"})
	@BeforeTest
	public static void startWebDriver(String browser) {
		if (browser.toLowerCase().contentEquals("chrome")) {
			System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.toLowerCase().contentEquals("ie")) {
			System.setProperty("webdriver.ie.driver","drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
	}
	
	public static void startHtmlUnitDriver() {
		driver = new HtmlUnitDriver();
	}
	
	@Parameters({"url"})
	@BeforeTest(dependsOnMethods={"startWebDriver"})
	public static void setUrl(String url) {
		driver.get(url);
	}
	
	@AfterTest(alwaysRun=true)
	public static void quit() {
		driver.quit();
	}
}