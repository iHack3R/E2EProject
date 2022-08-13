package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebPageLoginObjects {
    public WebDriver driver;
    public WebPageLoginObjects(WebDriver driver) {
        this.driver = driver;
    }

    //Page objects
    By login = By.className("theme-btn");
    By loginButton = By.xpath("//input[@data-testid='login-button']");
    By loginCTA = By.linkText("Login");
    By emailText = By.id("email");
    By passwordText = By.id("password");
    By loginErrorText = By.xpath("//main/div/div");

    //Page methods
    public WebElement getLogin() {
        return driver.findElement(login);
    }
    public WebElement loginButton() {
        return driver.findElement(loginButton);
    }
    public WebElement loginLink() {
        return driver.findElement(loginCTA);
    }
    public WebElement email() {
        return driver.findElement(emailText);
    }
    public WebElement password() {
        return driver.findElement(passwordText);
    }
    public WebElement loginError() {
        return driver.findElement(loginErrorText);
    }
}