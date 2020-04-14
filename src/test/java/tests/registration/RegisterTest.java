package tests.registration;

import drivers.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SignUpPage;
import utils.User;
import utils.UserUtil;

import java.io.File;

public class RegisterTest {

    private static WebDriver driver;

    public static void setupBrowser(){
        driver = Browser.getBrowser("chrome");
        driver.get(SignUpPage.pageUrl());
    }

    @BeforeMethod
    public void beforeSignUp(){
        setupBrowser();
    }
    @Test
    public void signUp(){
        User user = User.getUserFromJsonFile(RegistrationConfigJsonParser.validUserFileName());
        try{
            SignUpPage.firstNameInput(driver).sendKeys(user.getFirstName());
            SignUpPage.lastNameInput(driver).sendKeys(user.getLastName());
            SignUpPage.mobileNumberInput(driver).sendKeys(user.getMobile());
            SignUpPage.emailInput(driver).sendKeys(user.getEmail());
            SignUpPage.passwordInput(driver).sendKeys(user.getPassword());
            SignUpPage.confirmPasswordInput(driver).sendKeys(user.getPassword());
            SignUpPage.signUpButton(driver).submit();
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @AfterMethod
    public static void afterSignUp(){
        Browser.closeBrowser(driver);
    }
}
