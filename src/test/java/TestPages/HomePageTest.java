package TestPages;

import PageObjects.HomePageObjects;
import Resources.BaseFramework;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class HomePageTest extends BaseFramework {

    //Important to have driver declared locally as well in Test Classes to avoid errors in parallel tests execution
    public static WebDriver driver;

    public static String homepageText;

    @BeforeTest
    public void initialize() throws IOException {

        //Initialise driver
        driver = initializeDriver();
        log.info("WebDriver is initialised");

        String pageURL = property.getProperty("url");
        homepageText = property.getProperty("homepageHeroText");

        //Open required page
        driver.get(pageURL);
        log.info("Browser navigates to the provided URL");
    }

    @Test
    public void homePageTitle () {
        HomePageObjects home = new HomePageObjects(driver);
        String text = home.getHeroTitle().getText();
        log.info("Homepage hero title is extracted");
        log.info("Hero title is: " + text);
        Assert.assertEquals(homepageText, text);
        log.info("Assertion applied to compare hero title with required title");
    }

    @AfterTest
    public void tearDown () {
        driver.close();
        log.info("Active browser tab is closed");
    }
}