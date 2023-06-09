package solutions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RequestLoanPage extends BasePage {

    private final By textfieldLoanAmount = By.id("amount");
    private final By textfieldDownPayment = By.id("downPayment");
    private final By dropdownFromAccountId = By.id("fromAccountId");
    private final By buttonSubmitLoanRequest = By.xpath("//input[@value='Apply Now']");
    private final By textlabelLoanRequestSubmissionResult = By.id("loanStatus");
    private final By textlabelLoanProviderName = By.id("loanProviderName");
    private final By textlabelInternalErrorHasOccurred = By.xpath("//p[contains(text(),'An internal error has occurred and has been logged.')]");

    public RequestLoanPage(WebDriver driver) {
        super(driver);
    }

    public void submitLoanRequest(String loanAmount, String downPayment, String fromAccountId) {
        sendKeys(textfieldLoanAmount, loanAmount);
        sendKeys(textfieldDownPayment, downPayment);
        select(dropdownFromAccountId, fromAccountId);
        click(buttonSubmitLoanRequest);
    }

    public String getLoanApplicationResult() {
        return getElementText(textlabelLoanRequestSubmissionResult);
    }

    public String getLoanProviderName() {
        return getElementText(textlabelLoanProviderName);
    }

    public boolean internalErrorMessageIsVisible() {
        return isDisplayed(textlabelInternalErrorHasOccurred);
    }
}
