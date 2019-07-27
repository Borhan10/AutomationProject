# AutomationProject for crud operations
by Mohamed Ahmed Borhan, 2019/07/28

This is a Selenium project make CRUD operations on the [Computer website](http://computer-database.herokuapp.com/computers).

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
1. 

### Prerequisites

1. Eclipse 
2. Make sure to convert the project to TestNG project if its not converted
3. Open properties and make sure that JDK is higher than 1.5 (Open project properties=> Open Java compiler => Uncheck Use compliance from execution => Change to 1.8 => Apply and close)


## Running the tests

1.Open testng file and run the project as testng 
2.Add or remove any tests that need to run from the testng.xml file 
3.Make sure that test data are valid to run the tests (AutomationProject=>resources=>data.json)

### Test Cases
1. Create Computer.
1.1 Create a valid computer.
1.2 Check for computer number increase after make add.
1.3 Check the data that was entered and check that all values are identical.
1.4 Create computer without name
1.5 Create computer with invalid introduce date
1.6 Create computer with invalid disconnect date
2. Delete computer
2.1 Delete computer successfully and check for number of decreased computers 
2.2 Check for message of deletion 


### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
