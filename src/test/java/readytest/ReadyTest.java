package readytest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

public class ReadyTest {

    private WebDriver driver;

    @BeforeEach
    public void startBrowser() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");

        this.driver = new ChromeDriver(chromeOptions);
        this.driver.manage().window().maximize();
    }

    @Test
    public void navigateToParaBank() {

        this.driver.get("https://parabank.parasoft.com");

        assertEquals("ParaBank | Welcome | Online Banking", driver.getTitle());
    }

    @AfterEach
    public void stopBrowser() {

        this.driver.quit();
    }
}
