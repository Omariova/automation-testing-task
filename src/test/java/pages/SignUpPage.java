package pages;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.PageConfigJsonParser;

import java.io.File;

public class SignUpPage {

    private JSONObject locators;

    public SignUpPage(){
        this.locators = PageConfigJsonParser.locators(new File("src/test/java/pages/SignUpPageConfig.json"));
    }

    //First name input field
    public WebElement firstNameInput(WebDriver driver){
        return driver.findElement(By.xpath(locators.get("firstNameInput").toString()));
    }
    //Last name input field
    public WebElement lastNameInput(WebDriver driver){
        return driver.findElement(By.xpath(locators.get("lastNameInput").toString()));
    }
    //Mobile input field
    public WebElement mobileNumberInput(WebDriver driver){
        return driver.findElement(By.xpath(locators.get("mobileNumberInput").toString()));
    }
    //Email input field
    public WebElement emailInput(WebDriver driver){
        return driver.findElement(By.xpath(locators.get("emailInput").toString()));
    }
    //Password input field
    public WebElement passwordInput(WebDriver driver){
        return driver.findElement(By.xpath(locators.get("passwordInput").toString()));
    }
    //Confirm password input field
    public WebElement confirmPasswordInput(WebDriver driver){
        return driver.findElement(By.xpath(locators.get("confirmPasswordInput").toString()));
    }
    //Sign up button
    public WebElement signUpButton(WebDriver driver){
        return driver.findElement(By.xpath(locators.get("signUpButton").toString()));
    }
    //My account button after login
    public WebElement accountButtonAfterLogin(WebDriver driver){
        return driver.findElement(By.xpath(locators.get("accountButtonAfterLogin").toString()));
    }
}
