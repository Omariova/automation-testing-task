package drivers;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.proxy.CaptureType;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;
import org.json.simple.JSONObject;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.AppConfigJsonParser;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Browser {

    private WebDriver driver;
    private ProxyDriver proxyDriver;
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
                    this.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                    break;
                }
             //Initialize firefox driver not implemented yet.
            case "firefox":
                break;
        }
        return this.driver;
    }

    public ProxyDriver getProxyDriver(String browserName){
        switch (browserName){
            //Initialize chrome driver
            case "chrome":
                if(this.proxyDriver == null){
                    System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                    this.proxyDriver = new ProxyDriver();
                    this.proxyDriver.proxy = new BrowserMobProxyServer();
                    this.proxyDriver.proxy.start(0);

                    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(this.proxyDriver.proxy);

                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                    capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

                    this.proxyDriver.driver = new ChromeDriver(capabilities);

                    this.proxyDriver.proxy.enableHarCaptureTypes(
                            CaptureType.REQUEST_CONTENT,
                            CaptureType.RESPONSE_CONTENT,
                            CaptureType.REQUEST_HEADERS,
                            CaptureType.RESPONSE_HEADERS,
                            CaptureType.REQUEST_BINARY_CONTENT,
                            CaptureType.RESPONSE_BINARY_CONTENT,
                            CaptureType.REQUEST_COOKIES,
                            CaptureType.RESPONSE_COOKIES
                    );

                    this.proxyDriver.driver.manage().window().maximize();
                    this.proxyDriver.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                    break;
                }
                //Initialize firefox driver not implemented yet.
            case "firefox":
                break;
        }
        return this.proxyDriver;
    }

}
