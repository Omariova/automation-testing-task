package pages;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.PageConfigJsonParser;

import java.io.File;

public class LoginPage {

    private JSONObject locators;

    public LoginPage() {
        this.locators = PageConfigJsonParser.locators(new File("src/test/java/pages/LoginPageConfig.json"));
    }

    public WebElement myAccountButton(WebDriver driver){
        return driver.findElement(By.xpath(locators.get("myAccountButton").toString()));
    }

    public WebElement myAccountLoginButton(WebDriver driver){
        return driver.findElement(By.xpath(locators.get("myAccountLoginButton").toString()));
    }

    public WebElement emailInput(WebDriver driver){
       return driver.findElement(By.xpath(locators.get("emailInput").toString()));
    }

    public WebElement passwordInput(WebDriver driver){
        return driver.findElement(By.xpath(locators.get("passwordInput").toString()));
    }

    public WebElement loginButton(WebDriver driver){
        return driver.findElement(By.xpath(locators.get("loginButton").toString()));
    }
}
