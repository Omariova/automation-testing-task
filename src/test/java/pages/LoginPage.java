package pages;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.PageConfigJsonParser;

import java.io.File;

public class LoginPage {

    private static WebElement element;
    private static JSONObject jsonObject = PageConfigJsonParser.parser(new File("src/test/java/pages/LoginPageConfig.json"));

    //Returning page url from config file
    public static String pageUrl(){
        return jsonObject.get("url").toString();
    }

    public static WebElement emailInput(WebDriver driver){
        element = driver.findElement(By.xpath(jsonObject.get("emailInput").toString()));
        return element;
    }

    public static WebElement passwordInput(WebDriver driver){
        element = driver.findElement(By.xpath(jsonObject.get("passwordInput").toString()));
        return element;
    }

    public static WebElement loginButton(WebDriver driver){
        element = driver.findElement(By.xpath(jsonObject.get("loginButton").toString()));
        return element;
    }

}
