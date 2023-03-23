package challenges;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

public class Challenge3Test {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void startBrowser() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");

        this.driver = new ChromeDriver(chromeOptions);
        this.driver.manage().window().maximize();

        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
    }

    @Test
    public void submitLoanRequest_withinLimits_shouldBeAccepted() {

        // Open website
        driver.get("https://parabank.parasoft.com");

        // Log in
        sendKeys(By.name("username"),"john");
        sendKeys(By.name("password"), "demo");
        click(By.xpath("//input[@value='Log In']"));

        // Navigate to Loan Request form
        click(By.linkText("Request Loan"));

        // Submit loan request
        sendKeys(By.id("amount"), "1000");
        sendKeys(By.id("downPayment"), "100");
        select(By.id("fromAccountId"),"12345");
        click(By.xpath("//input[@value='Apply Now']"));

        // Check result
        String result = getElementText(By.id("loanStatus"));
        assertEquals("Approved", result);
    }

    @AfterEach
    public void closeBrowser() {

        this.driver.quit();
    }

    private void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    private void sendKeys(By locator, String textToType) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).sendKeys(textToType);
    }

    private void select(By locator, String valueToSelect) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        new Select(driver.findElement(locator)).selectByVisibleText(valueToSelect);
    }

    private String getElementText(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }
}
