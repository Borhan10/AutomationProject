package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class deletePage extends basePage{

	public deletePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = ".btn.danger")
	public WebElement deleteComputer;
	
	public void waitForPageToLoad() {   // Wait for page to load 
		waitForVisibilityOf(deleteComputer);
	}
	public void deleteComputer() {    // Press delete computer
			click(deleteComputer);
		}

}
