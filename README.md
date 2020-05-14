# Automation Test Task

This task is testing [**phptravels.net**](https://phptravels.net/) *registration process* and verifing the *login process* after successful registration.


## Getting Started

These instructions will get you a copy of the task up and running on your local machine.


### Prerequisites

You need to:

* Install Java JDK 11 and Maven 3

* Run ```mvn install``` to install the libraries from pom.xml.

* Download suitable [**Chrome WebDriver**](https://chromedriver.chromium.org/downloads) for your chrome version, 
and locate it in *`src/test/java/tests/drivers`* then check AppConfig.json file to verify the configuration.


### Testing Framework Structure

The Framework consist of **4** main packages

* **Drivers**: Contains the web drivers for the browsers and the required classes to build and configure the drivers.
* **Pages**:   Contains page models and their configuration files.
* **Tests**:   Contains the test cases classes, reports and test data.
* **Utils**:   Contains any helper classes like pasring and writing files, taking screen shots and the models that maps the test data.
* **AppConfig.json**: This file contains the app variables like test urls and drivers paths.


## Running the task

We need first to check the test data, so go to *`src/test/java/tests/test_data/data.json`*,
you will find user model data and assertion expected values.

Run this command in the terminal to start the test.
```
mvn test
```
The Registration and Login test cases will run sequencially. 

## Test Case Scenarios

#### Register with valid data scenario:
- The **Reports** will be generated in *`src/test/java/tests/reports`* and marked as passed.
- The **Sign Up Request and Response** saved in *`src/test/java/tests/RegisterTraffic.json`*.
- The registered **user email** will be saved in *`src/test/java/tests/test_data/users.json`*.

#### Register with not valid data scenario:
- The **Reports** will be generated in *`src/test/java/tests/reports`* and marked as failed with screen shots.
- The **Sign Up Request and Response** saved in *`src/test/java/tests/RegisterTraffic.json`*.
 
#### Register with already registered email scenario:
- The **Reports** will be generated in *`src/test/java/tests/reports`* and marked as failed with screen shots.
- The **Sign Up Request and Response** saved in *`src/test/java/tests/RegisterTraffic.json`*.

#### Login with not registered email scenario:
change the data.json email and enter not registered email
```
run mvn test -Dtest=LoginTest 
```
- The **Reports** will be generated in *`src/test/java/tests/reports`* and marked as failed with screen shots.

## Built with

* [**Maven**](https://maven.apache.org/) - Dependency Managment
* [**Selenium WebDriver**](https://www.selenium.dev/) - Testing Automation Tool
* [**TestNG**](https://testng.org/doc/) - Testing Framework
* [**BrowserMob Proxy**](https://github.com/lightbody/browsermob-proxy) - HTTP Network Traffic Tool
* [**Extent Reports**](https://extentreports.com/) - Extent Reporting Framework


