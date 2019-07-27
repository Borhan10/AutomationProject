package TestScenarios.AutomationProject;

import java.text.ParseException;

import org.testng.Assert;
import org.testng.annotations.*;

import pageobjects.deletePage;
import pageobjects.homePage;
import pageobjects.setComputerPage;

public class updateComputer extends baseTest {

	String oldComputerName, newComputerName, newIntroduceDate, newDisconnectDate, newCompany,newInvalidIntroDate,newInvalidDisconnDate;
	homePage home;
	setComputerPage update;
	deletePage delete;

	@BeforeTest
	public void init() {
		oldComputerName = jsonTestData.getData("updateComputer").get("oldComputerName");
		newComputerName = jsonTestData.getData("updateComputer").get("newComputerName");
		newIntroduceDate = jsonTestData.getData("updateComputer").get("newIntroduceDate");
		newDisconnectDate = jsonTestData.getData("updateComputer").get("newDisconnectDate");
		newInvalidIntroDate=jsonTestData.getData("invalidIntroDate").get("introduceDate");
		newInvalidDisconnDate=jsonTestData.getData("invalidDisconnDate").get("disconnectDate");
		newCompany = jsonTestData.getData("updateComputer").get("newCompany");
		home = new homePage(driver);
		update = new setComputerPage(driver);
		delete = new deletePage(driver);

	}

	@Test(priority=1)
	public void updateComputerSuccessfully() throws ParseException { // Select the computer that will be update 
		// Update computer data
		// Message appear successfully and has the correct value 
		home.openhome();
		home.waitForPagetoLoad();
		home.clearSearchBox();
		home.enterSearchValue(oldComputerName);
		home.pressSearch();
		home.selectFirstComputer();
		update.waitForPagetoLoad();
		update.setComputerName(newComputerName);
		update.setComputerIntroDate(newIntroduceDate);
		update.setComputerDiscoDate(newDisconnectDate);
		update.setComputerCompany(newCompany);
		update.pressSaveComputer();
		home.waitForPagetoLoad();
		Assert.assertTrue(home.computerAlertMessage());
		Assert.assertEquals(home.getAlertMessage(), "Done! Computer " + newComputerName + " has been updated",
				"Update message appear correct");
	}

	@Test(priority=2)
	public void updateComputerCheckValues() throws ParseException {
		home.openhome();
		home.waitForPagetoLoad();
		home.clearSearchBox();
		home.enterSearchValue(oldComputerName);
		home.pressSearch();
		home.selectFirstComputer();
		update.waitForPagetoLoad();
		update.setComputerName(newComputerName);
		update.setComputerIntroDate(newIntroduceDate);
		update.setComputerDiscoDate(newDisconnectDate);
		update.setComputerCompany(newCompany);
		update.pressSaveComputer();
		home.waitForPagetoLoad();
		home.clearSearchBox();
		home.enterSearchValue(newComputerName);
		home.pressSearch();
		Assert.assertTrue(home.searchResultValidation(newComputerName, newIntroduceDate, newDisconnectDate, newCompany),
				"Search for the updated computer");
	}

	@Test(priority=3)
	public void updateComputerWithInvalidName() { // Check that computer didn't update when introduce date is invalid
		home.openhome();
		home.waitForPagetoLoad();
		home.clearSearchBox();
		home.enterSearchValue(oldComputerName);
		home.pressSearch();
		Assert.assertEquals(home.selectFirstComputer(), true, "Search result first element detected");
		update.waitForPagetoLoad();
		update.clearComputerName();
		update.setComputerIntroDate(newIntroduceDate);
		update.setComputerDiscoDate(newDisconnectDate);
		update.setComputerCompany(newCompany);
		update.pressSaveComputer();
		Assert.assertEquals(update.invalidComputerDisplayError(), true,"Computer didn't updated and red highligh appear");

	}
	
	@Test(priority=4)
	public void updateComputerWithInvalidDisconnDate() {// Check that computer didn't update when disconnect date is invalid
		home.openhome();
		home.waitForPagetoLoad();
		home.clearSearchBox();
		home.enterSearchValue(oldComputerName);
		home.pressSearch();
		Assert.assertEquals(home.selectFirstComputer(), true, "Search result first element detected");
		update.waitForPagetoLoad();
		update.setComputerName(newComputerName);
		update.setComputerIntroDate(newIntroduceDate);
		update.setComputerDiscoDate(newInvalidDisconnDate);
		update.setComputerCompany(newCompany);
		update.pressSaveComputer();
		Assert.assertEquals(update.invalidComputerDisplayError(), true,"Computer didn't updated and red highligh appear");
	}
	
	@Test(priority=5)
	public void updateComputerWithInvalidIntroDate() {
		home.openhome();
		home.waitForPagetoLoad();
		home.clearSearchBox();
		home.enterSearchValue(oldComputerName);
		home.pressSearch();
		Assert.assertEquals(home.selectFirstComputer(), true, "Search result first element detected");
		update.waitForPagetoLoad();
		update.setComputerName(newComputerName);
		update.setComputerIntroDate(newInvalidIntroDate);
		update.setComputerDiscoDate(newDisconnectDate);
		update.setComputerCompany(newCompany);
		update.pressSaveComputer();
		Assert.assertEquals(update.invalidComputerDisplayError(), true,"Computer didn't updated and red highligh appear");
	}

	@AfterTest
	public void deleteAfterAdd() {
		home.openhome();
		home.clearSearchBox();
		home.enterSearchValue(newComputerName);
		home.pressSearch();
		if (home.searchhasResults()) {
			home.selectFirstComputer();
			delete.deleteComputer();
		}
	}
}
