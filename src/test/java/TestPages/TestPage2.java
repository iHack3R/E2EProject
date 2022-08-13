package TestPages;

import Resources.BaseFramework;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestPage2 {
    @Test
    public void invokeTestPage() throws IOException {
        BaseFramework browserSelection = new BaseFramework();
        WebDriver driver = browserSelection.initializeDriver();
        driver.get("https://www.apple.com/");
        String pageTitle = driver.getTitle();
        System.out.println(pageTitle);
        driver.close();
    }
}
