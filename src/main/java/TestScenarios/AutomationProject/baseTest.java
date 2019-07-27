package TestScenarios.AutomationProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utilities.jsonDataReader;
import utilities.screenShot;;

public class baseTest {

	public static WebDriver driver;

	public static jsonDataReader jsonTestData;

	public static String BaseURl;

		

	public enum BROWSERS {
		CHROME("Chrome"), FIREFOX("Firefox"); //introduce the drivers that can be selected

		private final String browserName;

		private BROWSERS(String browserName) {
			this.browserName = browserName;
		}

		public String getBrowserName() {
			return browserName;
		}
	}

	public enum OS {                                      //introduce the operating system that will be choos
		MAC("Mac OS"), WIN("Windows"), LINUX("Linux");

		private final String osName;

		private OS(final String osName) {
			this.osName = osName;
		}

		public String getOsName() {
			return osName;
		}
	}

	@BeforeSuite
	@Parameters({ "browser", "URL" })    // Set the browser name and the URL and get the required driver for OS
	public void startDriver(@Optional("chrome") String WindowBrowser,
			@Optional("http://computer-database.herokuapp.com/computers" + "") String URL)
			throws FileNotFoundException, IOException {
		jsonTestData = new jsonDataReader();

		final String os = System.getProperty("os.name");
		final String userDirectory = System.getProperty("user.dir");

		if (WindowBrowser.equalsIgnoreCase(BROWSERS.FIREFOX.getBrowserName())) {
			final StringBuilder geckoDriverPath = new StringBuilder();
			geckoDriverPath.append(userDirectory + File.separator + "resources");
			if (os.contains("Mac")) {
				geckoDriverPath.append(File.separator + "mac" + File.separator + "geckodriver");
			} else if (os.contains("Win")) {
				geckoDriverPath.append(File.separator + "win" + File.separator + "geckodriver.exe");
			}
			System.setProperty("webdriver.gecko.driver", geckoDriverPath.toString());
			driver = new FirefoxDriver();
		} else if (WindowBrowser.equalsIgnoreCase(BROWSERS.CHROME.getBrowserName())) {
			final StringBuilder chromeDriverPath = new StringBuilder();
			chromeDriverPath.append(userDirectory + File.separator + "resources");
			if (os.contains("Mac")) {
				chromeDriverPath.append(File.separator + "mac" + File.separator + "chromedriver");
			} else if (os.contains("Win")) {
				chromeDriverPath.append(File.separator + "win" + File.separator + "chromedriver.exe");
			}
			final StringBuilder geckoDriverPath = new StringBuilder();
			geckoDriverPath.append(userDirectory + File.separator + "resources");
			if (os.contains("Mac")) {
				geckoDriverPath.append(File.separator + "mac" + File.separator + "geckodriver");
			} else if (os.contains("Win")) {
				geckoDriverPath.append(File.separator + "win" + File.separator + "geckodriver.exe");
			}
			System.setProperty("webdriver.gecko.driver", geckoDriverPath.toString());
			System.setProperty("webdriver.chrome.driver", chromeDriverPath.toString());
			driver = new ChromeDriver();
		} 

		
		driver.manage().window().maximize();
		BaseURl = URL;
		driver.get(BaseURl);
	}

	@AfterSuite(alwaysRun = true)
	public void stopDriver() {
		driver.quit();
	}

	@AfterMethod   // Take a screen shot in case of failure 
	public void screeshotOnFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			screenShot.captureScreenshot(driver, result.getName());
		}
	}

}
