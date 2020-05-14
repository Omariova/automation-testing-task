package pages;

import drivers.ProxyDriver;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.filters.ResponseFilter;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.PageConfigJsonParser;
import utils.TestDataJsonParser;
import utils.User;

import java.io.File;
import java.io.FileWriter;

public class SignUpPage {

    private JSONObject locators;

    public SignUpPage() {
        this.locators = PageConfigJsonParser.locators(new File("src/test/java/pages/SignUpPageConfig.json"));
    }

    public WebElement firstNameInput(WebDriver driver) {
        return driver.findElement(By.name(locators.get("firstNameInput").toString()));
    }

    public WebElement lastNameInput(WebDriver driver) {
        return driver.findElement(By.name(locators.get("lastNameInput").toString()));
    }

    public WebElement mobileNumberInput(WebDriver driver) {
        return driver.findElement(By.name(locators.get("mobileNumberInput").toString()));
    }

    public WebElement emailInput(WebDriver driver) {
        return driver.findElement(By.name(locators.get("emailInput").toString()));
    }

    public WebElement passwordInput(WebDriver driver) {
        return driver.findElement(By.name(locators.get("passwordInput").toString()));
    }

    public WebElement confirmPasswordInput(WebDriver driver) {
        return driver.findElement(By.name(locators.get("confirmPasswordInput").toString()));
    }

    public WebElement signUpButton(WebDriver driver) {
        return driver.findElement(By.className(locators.get("signUpButton").toString()));
    }

    public WebElement accountButtonAfterRegister(WebDriver driver) {
        return driver.findElement(By.xpath(locators.get("accountButtonAfterLogin").toString()));
    }

    public void register(User user, ProxyDriver proxyDriver) {
        this.firstNameInput(proxyDriver.driver).sendKeys(user.getFirstName());
        this.lastNameInput(proxyDriver.driver).sendKeys(user.getLastName());
        this.mobileNumberInput(proxyDriver.driver).sendKeys(user.getMobile());
        this.emailInput(proxyDriver.driver).sendKeys(user.getEmail());
        this.passwordInput(proxyDriver.driver).sendKeys(user.getPassword());
        this.confirmPasswordInput(proxyDriver.driver).sendKeys(user.getPassword());

//        proxyDriver.proxy.addRequestFilter(
//                new RequestFilter() {
//                    @Override
//                    public HttpResponse filterRequest(HttpRequest httpRequest, HttpMessageContents httpMessageContents, HttpMessageInfo httpMessageInfo) {
//
//                        if (httpMessageInfo.getOriginalUrl().endsWith("/account/signup")) {
//                            try{
//                                System.out.println(httpMessageInfo.getOriginalUrl());
//                                System.out.println(httpMessageInfo.getOriginalRequest().toString());
//                                System.out.println(httpMessageContents.getTextContents());
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        return null;
//                    }
//                }
//        );

//        proxyDriver.proxy.addResponseFilter(
//                new ResponseFilter() {
//                    @Override
//                    public void filterResponse(HttpResponse httpResponse, HttpMessageContents httpMessageContents, HttpMessageInfo httpMessageInfo) {
//
//                        if(httpMessageInfo.getOriginalUrl().endsWith("/account/signup")) {
//                            try {
//                                System.out.println(httpMessageInfo.getOriginalUrl());
//                                System.out.println(httpMessageInfo.getOriginalRequest().toString());
//                                System.out.println(httpMessageContents.getTextContents());
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        System.out.println(httpMessageInfo.getOriginalUrl());
//                    }
//                }
//        );

        this.signUpButton(proxyDriver.driver).submit();

        TestDataJsonParser.addUserEmail(user.getEmail());
    }

    public String afterRegisterAccountButtonText(WebDriver driver) {
        return this.accountButtonAfterRegister(driver).getText().toLowerCase();
    }

    public String title(WebDriver driver) {
        return driver.getTitle().toLowerCase();
    }

    public String titleExpectedValue() {
        return TestDataJsonParser.loginAssertionValues().get("pageTitle").toString().toLowerCase();
    }
}
