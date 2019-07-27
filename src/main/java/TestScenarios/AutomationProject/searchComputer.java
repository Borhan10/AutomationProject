package TestScenarios.AutomationProject;

import org.testng.annotations.*;

import java.text.ParseException;

import org.testng.Assert;
import pageobjects.setComputerPage;
import pageobjects.homePage;

public class searchComputer extends baseTest {

	String computerName,introduceDate,disconnectDate,company,invalidComputerName ;
	homePage home;
	setComputerPage add;

	@BeforeTest
	public void init() {
		computerName = jsonTestData.getData("searchValues").get("computerName");
		introduceDate=jsonTestData.getData("searchValues").get("introduceDate");
		disconnectDate=jsonTestData.getData("searchValues").get("disconnectDate");
		company=jsonTestData.getData("searchValues").get("company");
		invalidComputerName=jsonTestData.getData("searchInvalidComputer").get("computerName");
		home= new homePage(driver);
		add=new setComputerPage(driver);
	}
	
	@Test(priority=1)
	public void searchForAvailableComputer() { // Check that search return values 
		home.openhome();
		home.waitForPagetoLoad();
		home.openAddComputer();
		add.waitForPagetoLoad();
		add.setComputerName(computerName);
		add.setComputerIntroDate(introduceDate);
		add.setComputerDiscoDate(disconnectDate);
		add.setComputerCompany(company);
		add.pressSaveComputer();
		home.clearSearchBox();
		home.enterSearchValue(computerName);
		home.pressSearch();
		Assert.assertTrue(home.searchhasResults(),"Computers found ");
	}
	@Test(priority=3)
	public void searchForUnavailableComputer()  {   // Check that search didn't return values with computer name not in the system
		home.openhome();
		home.clearSearchBox();
		home.enterSearchValue(invalidComputerName);
		home.pressSearch();
		Assert.assertEquals(home.searchhasResults(), false);;
	}

	@Test(priority=2)
	public void validateSearchValues() throws ParseException { // Check that all values in the search are match
		home.openhome();
		home.clearSearchBox();
		home.enterSearchValue(computerName);
		home.pressSearch();
		Assert.assertTrue(home.searchResultValidation(computerName, introduceDate, disconnectDate, company));
	}
}
