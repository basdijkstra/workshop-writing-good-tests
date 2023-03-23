package solutions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.http.ContentType;
import org.asynchttpclient.Request;
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
import solutions.pages.AccountsOverviewPage;
import solutions.pages.LoginPage;
import solutions.pages.RequestLoanPage;

import java.time.Duration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Solution3Test {

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

        // Open website and log in
        new LoginPage(this.driver)
                .loginAs("john", "demo");

        // Navigate to Loan Request form
        new AccountsOverviewPage(this.driver)
                .selectMenuItem("Request Loan");

        // Submit loan request
        RequestLoanPage requestLoanPage = new RequestLoanPage(this.driver);
        requestLoanPage.submitLoanRequest("1000", "100", "12345");

        // Check result
        assertEquals("Approved", requestLoanPage.getLoanApplicationResult());
    }

    @Test
    public void submitLoanRequest_withinLimits_shouldBeAccepted_viaApi() {

        given()
                .queryParam("customerId", 12212)
                .queryParam("amount", 1000)
                .queryParam("downPayment", 100)
                .queryParam("fromAccountId", 12345)
                .accept(ContentType.JSON)
                .when()
                .post("https://parabank.parasoft.com/parabank/services/bank/requestLoan")
                .then()
                .statusCode(200)
                .body("approved", equalTo(true));
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
