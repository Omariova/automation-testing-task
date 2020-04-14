package tests.login;

import drivers.Browser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.User;

public class LoginTest {

    private static WebDriver driver;

    public static void setupBrowser(){
        driver = Browser.getBrowser("chrome");
        driver.get(LoginPage.pageUrl());
    }

    @BeforeMethod
    public static void beforeLogin(){
        setupBrowser();
    }

    @Test
    public static void login(){
        User user = User.getUserFromJsonFile(LoginConfigJsonParser.validUserFileName());
        try{
            LoginPage.emailInput(driver).sendKeys(user.getEmail());
            LoginPage.passwordInput(driver).sendKeys(user.getPassword());
            LoginPage.loginButton(driver).click();
            Thread.sleep(10000);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @AfterMethod
    public static void afterLogin(){
        Browser.closeBrowser(driver);
    }
}
