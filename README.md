# AutomationProject for crud operations
by Mohamed Ahmed Borhan, 2019/07/28

This is a Selenium project make CRUD operations on the [Computer website](http://computer-database.herokuapp.com/computers).

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
1. 

### Prerequisites

1. Eclipse 

2. Make sure to convert the project to TestNG project if its not converted

3. Open properties and make sure that JDK is higher than 1.5 (Open project properties=> Open Java compiler => Uncheck Use compliance 
from execution => Change to 1.8 => Apply and close)


## Running the tests

1.Open testng file and run the project as testng 

2.Add or remove any tests that need to run from the testng.xml file 

3.Make sure that test data are valid to run the tests (AutomationProject=>resources=>data.json)

### Test Cases in the code 
1. Create Computer.

1.1 Create a valid computer.

1.2 Check for computer number increase after make add.

1.3 Check the data that was entered and check that all values are identical.

1.4 Create computer without name

1.5 Create computer with invalid introduce date

1.6 Create computer with invalid disconnect date

2. Delete computer

2.1 Delete computer successfully and check for number of decreased computers 

2.2 Check for message of successfully delete

3. Search for computer

3.1 Check that search for available computer return value

3.2 Check that search for unavailable computer doesn't return value

3.3 Check that all values of the computer are correct

4. Update Computer 

4.1 Update computer sucessfully and check for the message appear correct

4.2 Check for all values of the updated computer are correct 

4.3 Update computer with invalid or empty computer name 

4.4 Update computer with invalid introduce date

4.5 Update computer with invalid disconnect date 


### Manual test cases

Fine test cases in (AutomationProject=>resources=>CRUD TestCases.xlsx)



## Built With

* [Selenium webdriver](https://www.seleniumhq.org/projects/webdriver/) Selenium-WebDriver makes direct calls to the browser using each browser’s native support for automation.

* [Maven](https://maven.apache.org/) - Dependency Management

* [TestNg](testng.org) - Used for Annotations and Run your test


