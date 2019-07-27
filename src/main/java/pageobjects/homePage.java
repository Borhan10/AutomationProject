package pageobjects;

import java.text.ParseException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class homePage extends basePage {

	public homePage(WebDriver driver) {
		super(driver);
	}

	String oldCount,newCount;
	@FindBy(id = "add")
	public WebElement addButton;

	@FindBy(id = "searchbox")
	public WebElement searchBox;

	@FindBy(id = "searchsubmit")
	public WebElement searchButton;

	@FindBy(css = ".alert-message.warning")
	public WebElement Message;
	@FindBy(id = "searchbox")
	public WebElement searchbox;

	@FindBy(id = "searchsubmit")
	public WebElement searchbutton;

	@FindBy(xpath = "//*[@id=\"main\"]/table/tbody/tr/td[1]/a")
	public WebElement firstElementResultComputerName;

	@FindBy(xpath = "//*[@id=\"main\"]/table/tbody/tr[1]/td[2]")
	public WebElement firstElementResultIntroDate;

	@FindBy(xpath = "//*[@id=\"main\"]/table/tbody/tr[1]/td[3]")
	public WebElement firstElementResultDisconnectDate;

	@FindBy(xpath = "//*[@id=\"main\"]/table/tbody/tr[1]/td[4]")
	public WebElement firstElementResultIndustry;

	@FindBy(xpath = "//*[@id=\"main\"]/h1")
	public WebElement NotingFoundMessage;

	@FindBy(id = "main")
	public WebElement computersCount;

	public void waitForPagetoLoad() { // Wait for home page to load
		waitForVisibilityOf(addButton);
	}

	public void openAddComputer() { // Open add computer page
		click(addButton);
	}

	public boolean computerAlertMessage() { // Check if the alert message displayed or not
		waitForVisibilityOf(addButton);
		return elementIsVisible(Message);
	}

	public String getAlertMessage() { // Get the alert message text
		return Message.getText();
	}

	public void openhome() { // Open home page
		driver.get("http://computer-database.herokuapp.com/computers");
		oldCount = getText(computersCount);
	}

	public void enterSearchValue(String searchValue) { // Enter value in the search box
		setText(searchbox, searchValue);
	}

	public void pressSearch() { // Click on the filter button to search
		click(searchbutton);
	}

	public boolean searchhasResults() { // Check if there is any elements returned from the search
		if (NotingFoundMessage.getText().toLowerCase().contains("no computers found")) {
			return false;
		} else if (elementIsVisible(firstElementResultComputerName)) {
			return true;
		} else
			return false;
	}

	public void clearSearchBox() { // Clear any data in the search box
		searchBox.clear();
	}

	public boolean searchResultValidation(String computerName, String IntroDate, String disconnectDate, String company)
			throws ParseException { // This function validate that first element of the search is identical for all
									// fields
		waitForVisibilityOf(firstElementResultComputerName);
		boolean Introdates = true, Disconn = true;
		String introDateNewFormat, disconnectDateNewFormat;
		String[] tempIntroDate, tempDisconn;
		tempIntroDate = split(firstElementResultIntroDate.getText(), " ");
		tempDisconn = split(firstElementResultDisconnectDate.getText(), " ");
		if (tempIntroDate.length == 3) { // if the introduce date was available in the computer
			tempIntroDate[1] = formatMonth(tempIntroDate[1]); // Convert the month to numbers
			introDateNewFormat = tempIntroDate[2] + "-" + tempIntroDate[1] + "-" + tempIntroDate[0];
			if (IntroDate.contains(introDateNewFormat)) {
				Introdates = true;
			} else
				Introdates = false;
		}
		if (tempDisconn.length == 3) { // if disconnect date was available
			tempDisconn[1] = formatMonth(tempDisconn[1]); // Convert the month to numbers
			disconnectDateNewFormat = tempDisconn[2] + "-" + tempDisconn[1] + "-" + tempDisconn[0];
			if (disconnectDate.contains(disconnectDateNewFormat)) {
				Disconn = true;
			} else
				Disconn = false;
		}
		if (computerName.toLowerCase().contains(firstElementResultComputerName.getText().toLowerCase()) // Check if all
																										// elements are
																										// the same
				&& company.toLowerCase().contains(firstElementResultIndustry.getText().toLowerCase()) && Introdates
				&& Disconn) {
			return true;
		} else
			return false;
	}

	public boolean selectFirstComputer() { // Select first computer from the computers in the list
		if (elementIsVisible(firstElementResultComputerName)) {
			click(firstElementResultComputerName);
			return true;
		} else
			return false;
	}

	public boolean homeComputerCount(String type) {
		String oldCountArr[], newCountArr[];
		int oldCountNumber, newCountNumber;
		oldCountArr = split(oldCount, " ");
		newCount = getText(computersCount);
		newCountArr = split(newCount, " ");
		oldCountNumber = Integer.parseInt(oldCountArr[0]);
		newCountNumber = Integer.parseInt(newCountArr[0]);
		if (type.toLowerCase() == "inc") {
			if (newCountNumber == oldCountNumber + 1)
				return true;
			else
				return false;
		} else {
			if (newCountNumber == oldCountNumber - 1)
				return true;
			else
				return false;
		}
	}
	
	public void pageRefresh() {
		refreshPage();
	}

}
