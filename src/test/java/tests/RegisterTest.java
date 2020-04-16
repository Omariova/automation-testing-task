package tests;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import drivers.Browser;
import drivers.ProxyDriver;
import net.lightbody.bmp.core.har.Har;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SignUpPage;
import utils.*;

import java.io.File;

public class RegisterTest {

    private ProxyDriver proxyDriver;
    private ExtentReports extent;
    private ExtentTest logger;

    public void setupBrowser(){

        this.proxyDriver = new Browser().getProxyDriver("chrome");
        this.proxyDriver.driver.get(AppConfigJsonParser.url());
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
                this.proxyDriver.proxy.newHar("register");
                page.register(user, this.proxyDriver.driver);
                Har har = this.proxyDriver.proxy.getHar();
                har.writeTo(new File("src/test/java/tests/RegisterTraffic.json"));
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }
            Assert.assertEquals(page.afterRegisterAccountButtonText(this.proxyDriver.driver), user.getFirstName().toLowerCase());
            Assert.assertEquals(page.title(this.proxyDriver.driver), page.titleExpectedValue());
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
            logger.log(LogStatus.FAIL, logger.addScreenCapture(ScreenShot.capture(this.proxyDriver.driver, "RegisterTestCase")));
        }
        extent.endTest(logger);
        this.proxyDriver.driver.quit();
    }

    @AfterTest
    public void endReport(){
        extent.flush();
        extent.close();
    }
}
