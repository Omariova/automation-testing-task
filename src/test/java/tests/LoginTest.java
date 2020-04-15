package tests;

import drivers.Browser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.AppConfigJsonParser;
import utils.User;

public class LoginTest {

    private WebDriver driver;

    public void setupBrowser(){
        this.driver = new Browser().getDriver("chrome");
        this.driver.get(AppConfigJsonParser.url());
    }

    @BeforeMethod
    public void beforeLogin(){
        setupBrowser();
    }

    @Test
    public void login(){
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

    }

    @AfterMethod
    public void afterLogin(){
        this.driver.quit();
    }
}
