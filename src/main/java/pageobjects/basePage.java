package pageobjects;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class basePage {

	protected WebDriver driver;

	public int time = 150;

	Actions action;

	final WebDriverWait wait;

	basePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		action = new Actions(driver);
		wait = new WebDriverWait(driver, time);
	}

	public void click(WebElement element) { // Click on the web element
		element.click();
	}

	public void setText(WebElement element, String text) { // Set text to the web element
		element.clear();
		element.sendKeys(text);
	}
	
	public void clear(WebElement element) { // Set text to the web element
		element.clear();
	}
	
	public void selectValuefromDropDown(WebElement element, String text) { // Select value from the drop down by the
																			// text
		Select dropDown = new Select(element);
		dropDown.selectByVisibleText(text);
	}

	public boolean elementIsVisible(WebElement element) { // Check if element is visible or not
		try{
		    if(element.isDisplayed())
		        return true;
		    else
		        return false; 
		}catch (NoSuchElementException e) {
			return false;
		}

	}

	public String[] split(String value, String splitter) {  // Split date into array of string 
		String[] newValue;
		newValue = value.split(splitter);
		return newValue;
	}

	public String formatMonth(String month) throws ParseException { // Convert from month number 02 to month characters
																	// Feb or vice versa
		if (month.length() == 2) {
			// In case convert from number to characters
			SimpleDateFormat monthParse = new SimpleDateFormat("MM");
			SimpleDateFormat monthDisplay = new SimpleDateFormat("MMM");
			return monthDisplay.format(monthParse.parse(month));
		} else {
			// In case convert from characters to number
			SimpleDateFormat monthParse = new SimpleDateFormat("MMM");
			SimpleDateFormat monthDisplay = new SimpleDateFormat("MM");
			return monthDisplay.format(monthParse.parse(month));
		}
	}

	public void waitForVisibilityOf(WebElement element) {   // Wait for WebElement to be visible
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public String getText(WebElement element) {
		return element.getText();
	}
	
	public void refreshPage() {
		driver.navigate().refresh();
	} 
}
