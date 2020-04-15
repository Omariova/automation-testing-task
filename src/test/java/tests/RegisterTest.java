package tests;

import drivers.Browser;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SignUpPage;
import utils.AppConfigJsonParser;
import utils.User;

import java.io.File;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.logging.Level;

public class RegisterTest {

    private WebDriver driver;

    public void setupBrowser(){
        this.driver = new Browser().getDriver("chrome");
        this.driver.get(AppConfigJsonParser.url());
    }

    @BeforeMethod
    public void beforeSignUp(){
        setupBrowser();
    }

    @Test
    public void signUp(){
        User user = new User().getUser("validUser");
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
    }
    @AfterMethod
    public void afterSignUp(){
        this.driver.quit();
    }
}
