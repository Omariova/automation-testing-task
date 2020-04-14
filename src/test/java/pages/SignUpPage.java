package pages;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.PageConfigJsonParser;

import java.io.File;

public class SignUpPage {

    private static WebElement element = null;
    private static JSONObject jsonObject = PageConfigJsonParser.parser(new File("src/test/java/pages/SignUpPageConfig.json"));

    //Returning page url from config file
    public static String pageUrl(){
        return jsonObject.get("url").toString();
    }

    //First name input field
    public static WebElement firstNameInput(WebDriver driver){
        element = driver.findElement(By.xpath(jsonObject.get("firstNameInput").toString()));
        return element;
    }
    //Last name input field
    public static WebElement lastNameInput(WebDriver driver){
        element = driver.findElement(By.xpath(jsonObject.get("lastNameInput").toString()));
        return element;
    }
    //Mobile input field
    public static WebElement mobileNumberInput(WebDriver driver){
        element = driver.findElement(By.xpath(jsonObject.get("mobileNumberInput").toString()));
        return element;
    }
    //Email input field
    public static WebElement emailInput(WebDriver driver){
        element = driver.findElement(By.xpath(jsonObject.get("emailInput").toString()));
        return element;
    }
    //Password input field
    public static WebElement passwordInput(WebDriver driver){
        element = driver.findElement(By.xpath(jsonObject.get("passwordInput").toString()));
        return element;
    }
    //Confirm password input field
    public static WebElement confirmPasswordInput(WebDriver driver){
        element = driver.findElement(By.xpath(jsonObject.get("confirmPasswordInput").toString()));
        return element;
    }
    //Sign up button
    public static WebElement signUpButton(WebDriver driver){
        element = driver.findElement(By.xpath(jsonObject.get("signUpButton").toString()));
        return element;
    }
}
