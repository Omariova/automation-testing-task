package tests;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import drivers.Browser;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SignUpPage;
import utils.AppConfigJsonParser;
import utils.ScreenShot;
import utils.TestDataJsonParser;
import utils.User;

import java.io.File;

public class RegisterTest {

    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest logger;

    public void setupBrowser(){
        this.driver = new Browser().getDriver("chrome");
        this.driver.get(AppConfigJsonParser.url());
    }

    @BeforeMethod
    public void beforeSignUp(){
        extent = new ExtentReports("src/test/java/tests/reports/RegistrationTestReport.html", true);
        extent.loadConfig(new File("src/test/java/tests/extent-config.xml"));
        setupBrowser();
    }

    @Test
    public void signUp(){
        logger = extent.startTest("Register Test");
        User user = new User().getUser("validUser");
        if(user.isValidUser())
        {
            SignUpPage page = new SignUpPage();
            try{
                page.firstNameInput(driver).sendKeys(user.getFirstName());
                page.lastNameInput(driver).sendKeys(user.getLastName());
                page.mobileNumberInput(driver).sendKeys(user.getMobile());
                page.emailInput(driver).sendKeys(user.getEmail());
                page.passwordInput(driver).sendKeys(user.getPassword());
                page.confirmPasswordInput(driver).sendKeys(user.getPassword());
                page.signUpButton(driver).submit();
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }
            Assert.assertEquals(page.accountButtonAfterLogin(driver).getText().toLowerCase(), user.getFirstName().toLowerCase());
            Assert.assertEquals(driver.getTitle().toLowerCase(), TestDataJsonParser.signUpAssertionValues().get("pageTitle").toString().toLowerCase());
        } else{
            Assert.fail("User Not Valid");
        }
    }
    @AfterMethod
    public void afterSignUp(ITestResult result){
        if(result.getStatus() == ITestResult.SUCCESS){
            logger.log(LogStatus.PASS, "Test Case Passed is Register Test");
        }else if(result.getStatus() == ITestResult.FAILURE){
            logger.log(LogStatus.FAIL, "Test Case Failed is Register Test");
            logger.log(LogStatus.FAIL, logger.addScreenCapture(ScreenShot.capture(driver, "RegisterTestCase")));
        }
        extent.endTest(logger);
        this.driver.quit();
    }

    @AfterTest
    public void endReport(){
        extent.flush();
        extent.close();
    }
}
