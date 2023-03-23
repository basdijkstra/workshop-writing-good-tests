package suggestions;

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
import suggestions.pages.LoginPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Suggestions3Test {

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

        // One suggestion I have here would be to separate the what (what is the user
        // doing on the page?) from the how (how does the test identify the elements
        // needed to perform the test?). This improves readability and maintainability.

        // This particular pattern for UI automation is known as the Page Object pattern:
        // https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/

        // Can you create and use similar Page Objects for the remainder of the test
        // as I've done for the login page and the login action? How does this impact readability and
        // maintainability of this test?

        // Even though this vastly improves the quality of our test code,
        // there is something else entirely that might potentially be improved for this specific test and
        // the information it retrieves. Can you think of something?

        // Open website and log in
        new LoginPage(this.driver)
                .loginAs("john", "demo");

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
