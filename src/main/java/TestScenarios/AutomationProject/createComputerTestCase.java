package TestScenarios.AutomationProject;

import java.text.ParseException;

import org.testng.Assert;
import org.testng.annotations.*;
import pageobjects.setComputerPage;
import pageobjects.deletePage;
import pageobjects.homePage;

public class createComputerTestCase extends baseTest {

	String computerName, introduceDate, disconnectDate, company,invalidIntroDate,invalidDisconnDate;
	homePage home;
	setComputerPage add;
	deletePage delete;

	@BeforeTest
	public void init() {
		computerName = jsonTestData.getData("addComputer").get("computerName");
		introduceDate = jsonTestData.getData("addComputer").get("introduceDate");
		disconnectDate = jsonTestData.getData("addComputer").get("disconnectDate");
		company = jsonTestData.getData("addComputer").get("company");
		invalidIntroDate=jsonTestData.getData("invalidIntroDate").get("introduceDate");
		invalidDisconnDate=jsonTestData.getData("invalidDisconnDate").get("disconnectDate");
		home = new homePage(driver);
		add = new setComputerPage(driver);
		delete = new deletePage(driver);

	}

	@Test(priority = '1')
	public void addNewValidComputer() throws ParseException { // Add new valid computer successfully
		// Make sure alert message displayed
		// Make sure that alert message message displayed correct
		home.openhome();
		home.waitForPagetoLoad();
		home.openAddComputer();
		add.waitForPagetoLoad();
		add.setComputerName(computerName);
		add.setComputerIntroDate(introduceDate);
		add.setComputerDiscoDate(disconnectDate);
		add.setComputerCompany(company);
		add.pressSaveComputer();
		home.waitForPagetoLoad();
		Assert.assertTrue(home.computerAlertMessage());
		Assert.assertEquals(home.getAlertMessage(), "Done! Computer " + computerName + " has been created",
				"Add message appear");
	}

	@Test(priority=2)
	public void computerCounterIncrease() { 		// Make sure that computer number increased
		home.openhome();
		home.waitForPagetoLoad();
		home.openAddComputer();
		add.waitForPagetoLoad();
		add.setComputerName(computerName);
		add.setComputerIntroDate(introduceDate);
		add.setComputerDiscoDate(disconnectDate);
		add.setComputerCompany(company);
		add.pressSaveComputer();
		home.waitForPagetoLoad();
		home.refreshPage();
		Assert.assertTrue(home.homeComputerCount("inc"), "computer number increase");
	}

	@Test(priority=3)
	public void computerValuesAddedSuccessfully() throws ParseException { 	// Make sure that all added values are correct
		home.openhome();
		home.waitForPagetoLoad();
		home.openAddComputer();
		add.waitForPagetoLoad();
		add.setComputerName(computerName);
		add.setComputerIntroDate(introduceDate);
		add.setComputerDiscoDate(disconnectDate);
		add.setComputerCompany(company);
		add.pressSaveComputer();
		home.waitForPagetoLoad();
		home.clearSearchBox();
		home.enterSearchValue(computerName);
		home.pressSearch();
		Assert.assertTrue(home.searchResultValidation(computerName, introduceDate, disconnectDate, company),
				"Search for the added computer");

	}

	@Test(priority =4)
	public void addNewInvalidComputer() { // Add new invalid computer without computer name
		// Make sure that message appear
		home.openhome();
		home.openAddComputer();
		add.waitForPagetoLoad();
		add.setComputerIntroDate(introduceDate);
		add.setComputerDiscoDate(disconnectDate);
		add.setComputerCompany(company);
		add.pressSaveComputer();
		add.waitForPagetoLoad();
		Assert.assertTrue(add.invalidComputerDisplayError(),"Computer didn't updated and red highligh appear");
	}
	
	@Test(priority=5)
	public void addNewInvalidIntroduceDate() {
		home.openhome();
		home.openAddComputer();
		add.waitForPagetoLoad();
		add.setComputerName(computerName);
		add.setComputerIntroDate(invalidIntroDate);
		add.setComputerDiscoDate(disconnectDate);
		add.setComputerCompany(company);
		add.pressSaveComputer();
		add.waitForPagetoLoad();
		Assert.assertTrue(add.invalidComputerDisplayError(),"Computer didn't updated and red highligh appear");
	}
	
	@Test(priority=6)
	public void addNewInvalidDisconnDate() {
		home.openhome();
		home.openAddComputer();
		add.waitForPagetoLoad();
		add.setComputerName(computerName);
		add.setComputerIntroDate(introduceDate);
		add.setComputerDiscoDate(invalidDisconnDate);
		add.setComputerCompany(company);
		add.pressSaveComputer();
		add.waitForPagetoLoad();
		Assert.assertTrue(add.invalidComputerDisplayError(),"Computer didn't updated and red highligh appear");
	}


	@AfterTest
	public void deleteAfterAdd() { // Delete the computer after add it to avoid duplication
		home.clearSearchBox();
		home.enterSearchValue(computerName);
		home.pressSearch();
		if (home.searchhasResults()) {
			home.selectFirstComputer();
			delete.deleteComputer();
		}
	}
}
