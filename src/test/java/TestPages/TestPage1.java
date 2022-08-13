package TestPages;

import Resources.BaseFramework;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestPage1 extends BaseFramework {
    @Test
    public void invokeHomePage() throws IOException {
        driver = initializeDriver();
        driver.get("https://www.microsoft.com/");
        String pageTitle = driver.getTitle();
        System.out.println(pageTitle);
        driver.close();
    }
}
