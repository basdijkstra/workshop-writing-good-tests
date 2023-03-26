package solutions;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import solutions.pages.AccountsOverviewPage;
import solutions.pages.LoginPage;
import solutions.pages.RequestLoanPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@WireMockTest(httpPort = 9876)
public class Solution4Test {

    private WebDriver driver;

    @BeforeEach
    public void setupDriverAndLogInToParaBank() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");

        this.driver = new ChromeDriver(chromeOptions);
        this.driver.manage().window().maximize();

        new LoginPage(driver)
                .loginAs("john", "demo");
    }

    @Test
    public void createLoanFor10000_expectResultToBeApproved() {

        new AccountsOverviewPage(driver)
                .selectMenuItem("Request Loan");

        RequestLoanPage rlp = new RequestLoanPage(driver);
        rlp.submitLoanRequest("10000", "100", "12345");

        assertEquals(
                "No Creativity Loan Processor",
                rlp.getLoanProviderName()
        );

        assertEquals(
                "Approved",
                rlp.getLoanApplicationResult()
        );
    }

    @Test
    public void createLoanFor9000_expectResultToBeApproved_butSlower() {

        new AccountsOverviewPage(driver)
                .selectMenuItem("Request Loan");

        RequestLoanPage rlp = new RequestLoanPage(driver);
        rlp.submitLoanRequest("9000", "100", "12345");

        assertEquals(
                "Sorry that took me so long...",
                rlp.getLoanProviderName()
        );

        assertEquals(
                "Approved",
                rlp.getLoanApplicationResult()
        );
    }

    @Test
    public void createLoanFor8000_expectResultToBeDenied() {

        new AccountsOverviewPage(driver)
                .selectMenuItem("Request Loan");

        RequestLoanPage rlp = new RequestLoanPage(driver);
        rlp.submitLoanRequest("8000", "100", "12345");

        assertEquals(
                "Computer says NO",
                rlp.getLoanProviderName()
        );

        assertEquals(
                "Denied",
                rlp.getLoanApplicationResult()
        );
    }

    @Test
    public void createLoanFor7000_expectInternalErrorToOccur() {

        new AccountsOverviewPage(driver)
                .selectMenuItem("Request Loan");

        RequestLoanPage rlp = new RequestLoanPage(driver);
        rlp.submitLoanRequest("7000", "100", "12345");

        assertTrue(
                rlp.internalErrorMessageIsVisible()
        );
    }

    @AfterEach
    public void closeBrowser() {

        driver.quit();
    }
}
