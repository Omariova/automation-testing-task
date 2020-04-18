package pages;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.PageConfigJsonParser;
import utils.TestDataJsonParser;
import utils.User;

import java.io.File;

public class SignUpPage {

    private JSONObject locators;

    public SignUpPage(){
        this.locators = PageConfigJsonParser.locators(new File("src/test/java/pages/SignUpPageConfig.json"));
    }

    public WebElement firstNameInput(WebDriver driver){
        return driver.findElement(By.name(locators.get("firstNameInput").toString()));
    }

    public WebElement lastNameInput(WebDriver driver){
        return driver.findElement(By.name(locators.get("lastNameInput").toString()));
    }

    public WebElement mobileNumberInput(WebDriver driver){
        return driver.findElement(By.name(locators.get("mobileNumberInput").toString()));
    }

    public WebElement emailInput(WebDriver driver){
        return driver.findElement(By.name(locators.get("emailInput").toString()));
    }

    public WebElement passwordInput(WebDriver driver){
        return driver.findElement(By.name(locators.get("passwordInput").toString()));
    }

    public WebElement confirmPasswordInput(WebDriver driver){
        return driver.findElement(By.name(locators.get("confirmPasswordInput").toString()));
    }

    public WebElement signUpButton(WebDriver driver){
        return driver.findElement(By.className(locators.get("signUpButton").toString()));
    }

    public WebElement accountButtonAfterRegister(WebDriver driver){
        return driver.findElement(By.xpath(locators.get("accountButtonAfterLogin").toString()));
    }

    public void register(User user, WebDriver driver){
        this.firstNameInput(driver).sendKeys(user.getFirstName());
        this.lastNameInput(driver).sendKeys(user.getLastName());
        this.mobileNumberInput(driver).sendKeys(user.getMobile());
        this.emailInput(driver).sendKeys(user.getEmail());
        this.passwordInput(driver).sendKeys(user.getPassword());
        this.confirmPasswordInput(driver).sendKeys(user.getPassword());
        this.signUpButton(driver).submit();
        TestDataJsonParser.addUserEmail(user.getEmail());
    }

    public String afterRegisterAccountButtonText(WebDriver driver){
        return this.accountButtonAfterRegister(driver).getText().toLowerCase();
    }

    public String title(WebDriver driver){
        return driver.getTitle().toLowerCase();
    }

    public String titleExpectedValue(){
        return TestDataJsonParser.loginAssertionValues().get("pageTitle").toString().toLowerCase();
    }
}
