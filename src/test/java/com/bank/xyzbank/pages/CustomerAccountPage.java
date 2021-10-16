package com.bank.xyzbank.pages;

import com.bank.xyzbank.helpers.SelectHelper;
import com.bank.xyzbank.helpers.WaitHelper;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Kreminskyi A.A. on авг., 2021
 */
public class CustomerAccountPage extends BasePage {

    WaitHelper waitHelper;
    SelectHelper selectHelper;

    public CustomerAccountPage(WebDriver driver) {
        super(driver);
        waitHelper = new WaitHelper(driver);
        selectHelper = new SelectHelper(driver);
    }

    @FindBy(css = "select[id='accountSelect']")
    private WebElement selectAccountElement; // Select web element to choose account

    @FindBy(css = ".center strong:nth-child(1)")
    private WebElement accountNumberValue; // Account number

    @FindBy(xpath = "//strong[contains(text(), 'Rupee')]")
    private WebElement currencyRupee; // Currency in rupee

    @FindBy(xpath = "//strong[contains(text(), 'Dollar')]")
    private WebElement currencyDollar; // Currency in dollar

    @FindBy(xpath = "//strong[contains(text(), 'Pound')]")
    private WebElement currencyPound; // Currency in pound

    @FindBy(css = ".center strong:nth-child(2)")
    private WebElement balanceValue;

    @FindBy(css = "input[type='number']")
    private WebElement inputWithdraw;

    @FindBy(css = "button[ng-click='deposit()']")
    private WebElement buttonDeposit; // Button for create deposit

    @FindBy(css = "td[class=ng-binding]")
    private WebElement transactionItem; // Item of transaction list

    @FindBy(css = "button[ng-click='transactions()']")
    private WebElement goToTransactions; // Button goes to page with transactions list

    @FindBy(css = "button[ng-click='back()']")
    private WebElement backToCustomerPageButton; // Button goes to page with customer account

    @FindBy(css = "button[ng-click='withdrawl()']")
    private WebElement withdrawButton; // Button for create withdraw

    @FindBy(css = "span[class='fontBig ng-binding']")
    private WebElement nameOfAccount;

    @FindBy(css = "input[ng-model='amount']")
    private WebElement insertDeposit;

    @FindBy(css = ".btn-default")
    private WebElement buttonConfirmDeposit;

    @FindBy(css = "form .btn-default")
    private WebElement buttonConfirmWithdraw;


    /**
     * Choose account by number
     */
    public void chooseAccountNumber(String number) {
        selectHelper.selectByVisibleText(selectAccountElement, number);
    }

    /**
     * Create deposit with waiting input and button element
     */
    public void createDeposit(String depositValue) {
        buttonDeposit.click();
        waitHelper.waitElementClickableWithClearAndSendText(insertDeposit, 10, depositValue);
        waitHelper.waitElementClickableAndClick(buttonConfirmDeposit, 10);
    }

    /**
     * Check current balance value
     */
    public String currentBalanceValue() {
        return waitHelper.waitElementClickable(balanceValue, 10).getText();
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
    public void createWithdraw(String withdrawValue) {
        withdrawButton.click();
        waitHelper.waitElementClickableWithClearAndSendText(inputWithdraw, 10, withdrawValue);
        waitHelper.waitElementClickableAndClick(buttonConfirmWithdraw, 10);
    }

    public String getAccountNumber() {
        return accountNumberValue.getText();
    }

    public boolean isDisplayedCurrencyRupee() {
        return currencyRupee.isDisplayed();
    }

    public boolean isDisplayedCurrencyDollar() {
        return currencyDollar.isDisplayed();
    }

    public boolean isDisplayedCurrencyPound() {
        return currencyPound.isDisplayed();
    }

    public void backToCustomerTab() {
        backToCustomerPageButton.click();
    }

    public void goToTransactionTab() {
        goToTransactions.click();
    }

    public String getNameOfCurrentAccount() {
        return nameOfAccount.getText();
    }
}
