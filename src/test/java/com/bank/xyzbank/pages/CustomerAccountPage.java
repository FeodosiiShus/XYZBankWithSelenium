package com.bank.xyzbank.pages;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Kreminskyi A.A. on авг., 2021
 */
public class CustomerAccountPage extends BasePage {

    public CustomerAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "select[id='accountSelect']")
    private WebElement selectAccountElement; // Select web element to choose account

    @FindBy(css = ".center strong:nth-child(1)")
    private WebElement accountNumberValue; // Account number

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

    @FindBy(css = ".error")
    private WebElement errorMessage;

    @FindBy(css = "button[ng-click='reset()']")
    private WebElement resetTransactionButton;

    /**
     * Choose account by number
     */
    public void chooseAccountNumber(String number) {
        logger.info("* Choose account number *");
        selectHelper.selectByVisibleText(selectAccountElement, number);
    }

    /**
     * Create deposit with waiting input and button element
     */
    public void createDeposit(String depositValue) {
        logger.info("* Create deposit *");
        buttonDeposit.click();
        waitHelper.waitElementClickableWithClearAndSendText(insertDeposit, 10, depositValue);
        waitHelper.waitElementClickableAndClick(buttonConfirmDeposit, 10);
    }

    /**
     * Check current balance value
     */
    public String currentBalanceValue() {
        logger.info("* Return current balance value *");
        return waitHelper.waitElementClickable(balanceValue, 10).getText();
    }

    /**
     * Check transaction exist by present element on page
     */
    public boolean checkTransactionsIsExist() {
        logger.info("* Check transactions is exist *");
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
        logger.info("* Create withdraw *");
        withdrawButton.click();
        waitHelper.waitElementClickableWithClearAndSendText(inputWithdraw, 10, withdrawValue);
        waitHelper.waitElementClickableAndClick(buttonConfirmWithdraw, 10);
    }

    public String getAccountNumber() {
        logger.info("* Get account number *");
        return accountNumberValue.getText();
    }

    public void goToCustomerTab() {
        logger.info("* Go to customer tab *");
        backToCustomerPageButton.click();
    }

    public void goToTransactionTab() {
        logger.info("* Go to transaction tab *");
        goToTransactions.click();
    }

    public String getNameOfCurrentAccount() {
        logger.info("* Get name of current account *");
        return nameOfAccount.getText();
    }

    public String getErrorMessageWithdraw() {
        return errorMessage.getText();
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessage.isDisplayed();
    }

    public void deleteAllTransactions() {
        try {
            resetTransactionButton.click();
            logger.info("* All transactions delete successfully *");
        } catch (ElementNotInteractableException exception) {
            logger.info("* Element not found on page: " + exception.getStackTrace() + " *");
        }
    }
}
