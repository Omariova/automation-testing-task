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
import pages.LoginPage;
import utils.AppConfigJsonParser;
import utils.ScreenShot;
import utils.TestDataJsonParser;
import utils.User;

import java.io.File;

public class LoginTest {

    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest logger;

    public void setupBrowser(){
        this.driver = new Browser().getDriver("chrome");
        this.driver.get(AppConfigJsonParser.url());
    }

    @BeforeMethod
    public void beforeLogin(){
        extent = new ExtentReports("src/test/java/tests/reports/LoginTestReport.html", true);
        extent.loadConfig(new File("src/test/java/tests/extent-config.xml"));
        setupBrowser();
    }

    @Test
    public void login(){
        logger = extent.startTest("Login Test");
        User user = new User().getUser("validUser");
        LoginPage page = new LoginPage();
        try{
            page.myAccountButton(driver).click();
            page.myAccountLoginButton(driver).click();
            Thread.sleep(3000);
            page.emailInput(driver).sendKeys(user.getEmail());
            page.passwordInput(driver).sendKeys(user.getPassword());
            page.loginButton(driver).click();
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertEquals(page.accountButtonAfterLogin(driver).getText().toLowerCase(), user.getFirstName().toLowerCase());
        Assert.assertEquals(driver.getTitle().toLowerCase(), TestDataJsonParser.loginAssertionValues().get("pageTitle").toString().toLowerCase());

    }

    @AfterMethod
    public void afterLogin(ITestResult result){
        if(result.getStatus() == ITestResult.SUCCESS){
            logger.log(LogStatus.PASS, "Test Case Passed is Login Test");
        }else if(result.getStatus() == ITestResult.FAILURE){
            logger.log(LogStatus.FAIL, "Test Case Failed is Login Test");
            logger.log(LogStatus.FAIL, logger.addScreenCapture(ScreenShot.capture(driver, "LoginTestCase")));
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
