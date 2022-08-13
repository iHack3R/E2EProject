package Resources;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseFramework {
    public static Properties property;
    public static Logger log;
    public static WebDriver driver;

    public static void globalProperties() throws IOException {
        //Global variable declaration
        property = new Properties();
        FileInputStream file = new FileInputStream("/Users/uditkumar/Library/Mobile Documents/com~apple~CloudDocs/IdeaProjects/E2EProject/src/main/java/Resources/data.properties");
        property.load(file);
    }

    public static void logger() {
        //Log4j logging declaration
        log = LogManager.getLogger(BaseFramework.class.getName());
    }

    public static WebDriver initializeDriver() throws IOException {

        //Browser selection
        globalProperties();
        logger();
        String browserName = property.getProperty("browser");
        String browserErrorMessage = property.getProperty("browserError");
        if (browserName.equals("Edge")) {
            System.setProperty("webdriver.edge.driver", "/Users/uditkumar/Downloads/Browser Drivers/msedgedriver");
            driver = new EdgeDriver();
        }
        else if (browserName.equals("Firefox")) {
            System.setProperty("webdriver.gecko.driver", "/Users/uditkumar/Downloads/Browser Drivers/geckodriver");
            driver = new FirefoxDriver();
        }
        else {
            log.error(browserErrorMessage);
        }

        //Defining implicit wait
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        return driver;
    }
    public String getScreenShot(String methodName, WebDriver screenshotDriver) throws IOException {
        TakesScreenshot screenshotObject = (TakesScreenshot) screenshotDriver;
        File screenshotFile = screenshotObject.getScreenshotAs(OutputType.FILE);
        String screenshotFilePath = System.getProperty("user.dir")+"/screenshots/"+methodName+".jpg";
        FileUtils.copyFile(screenshotFile, new File(screenshotFilePath));
        return screenshotFilePath;
    }
}