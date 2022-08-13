package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageObjects {
    public WebDriver driver;
    public HomePageObjects(WebDriver driver) {
        this.driver = driver;
    }

    //Page objects
    By heroTitle = By.className("headline");

    //Page methods
    public WebElement getHeroTitle() {
        return driver.findElement(heroTitle);
    }
}