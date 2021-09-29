package com.bank.xyzbank.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Kreminskyi A.A. on авг., 2021
 */
public class CustomerAccountPage {

    public CustomerAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "select[id='accountSelect']")
    private WebElement selectAccountElement; // Select web element to choose account

    @FindBy(css = "div:nth-child(3) > strong:nth-child(1)") // TODO: refactor locator account number
    public WebElement accountNumberValue; // Account number

    @FindBy(xpath = "//strong[contains(text(), 'Rupee')]")
    public WebElement currencyRupee; // Currency in rupee

    @FindBy(xpath = "//strong[contains(text(), 'Dollar')]")
    public WebElement currencyDollar; // Currency in dollar

    @FindBy(xpath = "//strong[contains(text(), 'Pound')]")
    public WebElement currencyPound; // Currency in pound

    @FindBy(xpath = "//div[2]/div/div[2]/strong[2]") // TODO: refactor locator balance value
    public WebElement balanceValue; // Value of account balance

    @FindBy(css = "button[ng-click='deposit()']")
    private WebElement buttonDeposit; // Button for create deposit

    @FindBy(css = "td[class=ng-binding]")
    private WebElement transactionItem; // Item of transaction list

    @FindBy(css = "button[ng-click='transactions()']")
    public WebElement goToTransactions; // Button goes to page with transactions list

    @FindBy(css = "button[ng-click='back()']")
    public WebElement backToCustomerPageButton; // Button goes to page with customer account

    @FindBy(css = "button[ng-click='withdrawl()']")
    public WebElement withdrawButton; // Button for create withdraw

    @FindBy(css = "span[class='fontBig ng-binding']")
    private WebElement nameOfAccount;


    private Select selectAccount; // Select element to choose account

    /**
     * Choose account by number
     */
    public void chooseAccountNumber(String number) {
        selectAccount = new Select(selectAccountElement);
        selectAccount.selectByVisibleText(number);
    }

    /**
     * Create deposit with waiting input and button element
     */
    public void createDeposit(String depositValue, WebDriver driver) { //TODO: add comments to waitElements
        buttonDeposit.click();
        WebElement waitInsertDepositValue = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[ng-model='amount']")));
        waitInsertDepositValue.sendKeys(depositValue);
        WebElement waitButtonConfirmDeposit = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("btn-default")));
        waitButtonConfirmDeposit.click();
    }

    /**
     * Check current balance value
     */
    public String currentBalanceValue(WebDriver driver) {
        WebElement waitBalanceValue = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div[2]/strong[2]"))); //TODO: refactor to new locator
        return waitBalanceValue.getText();
    }

    /**
     * Check transaction exist by present element on page
     */
    public boolean checkTransactionsIsExist() {
        try {
            return transactionItem.isDisplayed();
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    /**
     * Create withdraw with waiting input and button element
     */
    public void createWithdraw(String withdrawValue, WebDriver driver) {
        withdrawButton.click();
        WebElement waitInputWithdraw = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='number']")));
        waitInputWithdraw.sendKeys(withdrawValue);

        WebElement waitButtonConfirmWithdraw = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/button")));
        waitButtonConfirmWithdraw.click();
    }

    /**
     * Check some element exists
     */
    public boolean checkWebElementExist(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException exception) {
            return false;
        }

    }

    public String getNameOfCurrentAccount(){
        return nameOfAccount.getText();
    }
}
