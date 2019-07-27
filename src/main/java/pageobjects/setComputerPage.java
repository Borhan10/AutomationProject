package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class setComputerPage extends basePage {

	public setComputerPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id = "name")
	public WebElement computerName;
	@FindBy(id = "introduced")
	public WebElement IntroDate;
	@FindBy(id = "discontinued")
	public WebElement DiscoDate;
	@FindBy(id = "company")
	public WebElement company;
	@FindBy(css = ".btn.primary")
	public WebElement addComputer;
	@FindBy(css=".clearfix.error")
	public WebElement errorMessage;
	
	public void waitForPagetoLoad() {      // wait for page to load
		waitForVisibilityOf(computerName);
	}

	public void setComputerName(String computerName) {   // Set computer name field
		if(!computerName.isEmpty())
		setText(this.computerName, computerName);
	}

	public void setComputerIntroDate(String IntroDate) {  // Set computer introduce date
		if(!IntroDate.isEmpty())
		setText(this.IntroDate, IntroDate);
	}

	public void setComputerDiscoDate(String DiscoDate) {   // Set computer disconnect date 
		if(!DiscoDate.isEmpty())
		setText(this.DiscoDate, DiscoDate);
	}

	public void setComputerCompany(String company) {      // Select computer company from drop down
		if(!company.isEmpty())
		selectValuefromDropDown(this.company, company);
	}

	public void pressSaveComputer() {    // Press add computer 
		click(addComputer);
	}
	
	public boolean invalidComputerDisplayError() {    // Check for error message displayed 
		return elementIsVisible(errorMessage);
	}
	
	public void clearComputerName() {
		clear(computerName);
	}
}
