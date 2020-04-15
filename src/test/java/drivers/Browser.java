package drivers;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.AppConfigJsonParser;

import java.io.File;

public class Browser {

    private WebDriver driver;
    private JSONObject drivers;
    private String chromeDriverPath;

    public Browser(){
        drivers = AppConfigJsonParser.drivers();
        chromeDriverPath = drivers.get("webdriver.chrome.driver").toString();
    }
    //Return a custom driver based on the browser name
    public WebDriver getDriver(String browserName){
        switch (browserName){
            //Initialize chrome driver
            case "chrome":
                if(this.driver == null){
                    System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                    this.driver = new ChromeDriver();
                    this.driver.manage().window().maximize();
                    break;
                }
             //Initialize firefox driver not implemented yet.
            case "firefox":
                break;
        }
        return this.driver;
    }
}
