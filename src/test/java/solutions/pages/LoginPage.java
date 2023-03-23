package solutions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final String URL = "https://parabank.parasoft.com";

    private final By textfieldUsername = By.name("username");
    private final By textfieldPassword = By.name("password");
    private final By buttonDoLogin = By.xpath("//input[@value='Log In']");

    public LoginPage(WebDriver driver) {
        super(driver);
        driver.get(URL);
    }

    public void loginAs(String username, String password) {
        sendKeys(textfieldUsername, username);
        sendKeys(textfieldPassword, password);
        click(buttonDoLogin);
    }
}
