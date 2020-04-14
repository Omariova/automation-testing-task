package drivers;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.BrowserConfigJsonParser;

import java.io.File;

public class Browser {

    private static JSONObject jsonObject = BrowserConfigJsonParser.parser(new File("src/test/java/drivers/BrowserConfig.json"));
    private static WebDriver driver;

    //Get a custom driver based on the browser name
    public static WebDriver getBrowser(String browserName){
        switch (browserName){
            //Initialize chrome driver
            case "chrome":
                if(driver == null){
                    System.setProperty("webdriver.chrome.driver", jsonObject.get("webdriver.chrome.driver").toString());
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    break;
                }
             //Initialize firefox driver not implemented yet.
            case "firefox":
                break;
        }
        return driver;
    }
    //Close the driver
    public static void closeBrowser(WebDriver driver){
        if(driver != null) {
            driver.quit();
        }
    }


    public static void main(String args[]){
        System.out.println(jsonObject.toJSONString());
    }
}
