package TestPages;

import PageObjects.WebPageLoginObjects;
import Resources.BaseFramework;
import Resources.TestData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class WebPageLoginTest extends BaseFramework {

    //Important to have driver declared locally as well in Test Classes to avoid errors in parallel tests execution
    public static WebDriver driver;

    @BeforeTest
    public void initialize() throws IOException {

        //Initialise driver
        driver = initializeDriver();
        log.info("WebDriver is initialised");
    }

    @Test(dataProvider = "testDataForLogin", dataProviderClass = TestData.class)
    public void login(String testDataUserName, String testDataPassword, String userType) throws IOException {

        String pageURL = property.getProperty("url");
        String userName = property.getProperty("username");
        String password = property.getProperty("password");

        //Open required page
        driver.get(pageURL);
        log.info("Browser navigates to the provided URL");

        WebPageLoginObjects page = new WebPageLoginObjects(driver);
        page.getLogin().click();
        log.info("Login link on top of the page is clicked");
        page.loginLink().click();
        log.info("Login link is clicked from account creation window");
        page.email().sendKeys(testDataUserName);
        log.info("Username is entered");
        page.password().sendKeys(testDataPassword);
        log.info("Password is entered");
        page.loginButton().click();
        log.info("Login button is clicked");
        log.info("User Type: " + userType);
        String actualText = page.loginError().getText();
        String expectedText = property.getProperty("invalidLoginError");
        log.info("Login Error message: " + actualText);
        Assert.assertEquals(actualText,expectedText);
        log.info("Assertion applied to compare login error message with required error message");
    }

    @AfterTest
    public void tearDown () {
        driver.close();
        log.info("Active browser tab is closed");
    }
}