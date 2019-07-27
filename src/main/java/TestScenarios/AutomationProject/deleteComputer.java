package TestScenarios.AutomationProject;
import org.testng.Assert;
import org.testng.annotations.*;

import pageobjects.deletePage;
import pageobjects.homePage;
public class deleteComputer extends baseTest {
	
	homePage home;
	deletePage delete;
	String computerName ;
 
	
	
	@BeforeTest
	public void init() {
		computerName = jsonTestData.getData("deleteComputer").get("computerName");
		home= new homePage(driver);
		delete=new deletePage(driver);
	
	}
	
	@Test(priority=1)
	public void deleteComputerSuccessfully() { // Delete specific unique computer that is available in the system and 
		//get the success message  
		// Make sure that number of computers decrease
		home.openhome();
		home.waitForPagetoLoad();
		home.clearSearchBox();
		home.enterSearchValue(computerName);
		home.pressSearch();
		Assert.assertEquals(home.selectFirstComputer(),true,"Search result first element detected");
		delete.waitForPageToLoad();
		delete.deleteComputer();
		home.waitForPagetoLoad();
		Assert.assertEquals(home.getAlertMessage(), "Done! Computer has been deleted","Message appear ");
		home.refreshPage();
		Assert.assertTrue(home.homeComputerCount("dec"),"computer number decrease");
	}
	

	@Test(priority=2)
	public void checkNoSearchResults() { // Check No results found after delete 
		home.openhome();
		home.waitForPagetoLoad();
		home.clearSearchBox();
		home.enterSearchValue(computerName);
		home.pressSearch();
		Assert.assertFalse(home.searchhasResults(),"No results found for search");
	}

}
