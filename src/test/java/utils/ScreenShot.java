package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShot {
    public static String capture(WebDriver driver, String name){
        String dataName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot sc = (TakesScreenshot) driver;
        File source = sc.getScreenshotAs(OutputType.FILE);
        String validSource = "FailedTests/"+name+dataName+".png";
        String destination = "src/test/java/tests/reports/" + validSource;
        File finalDestination = new File(destination);
        try{
            FileUtils.copyFile(source, finalDestination);
        }catch (Exception e){e.printStackTrace();}
        return validSource;
    }
}
