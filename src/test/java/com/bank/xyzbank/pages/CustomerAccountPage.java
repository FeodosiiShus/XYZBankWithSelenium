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

    @FindBy(css = "select[id='accountSelect']") //TODO: add comment to elements
    private WebElement selectElement;

    @FindBy(css = "div:nth-child(3) > strong:nth-child(1)")
    public WebElement accountNumberValue;

    @FindBy(xpath = "//strong[contains(text(), 'Rupee')]")
    public WebElement currencyRupee;

    @FindBy(xpath = "//strong[contains(text(), 'Dollar')]")
    public WebElement currencyDollar;

    @FindBy(xpath = "//strong[contains(text(), 'Pound')]")
    public WebElement currencyPound;

    @FindBy(xpath = "//div[2]/div/div[2]/strong[2]")
    public WebElement balanceValue;

    @FindBy(css = "button[ng-click='deposit()']")
    private WebElement buttonDeposit;

    @FindBy(css = "td[class=ng-binding]")
    private WebElement transactionItem;

    @FindBy(css = "button[ng-click='transactions()']")
    public WebElement goToTransactions;

    @FindBy(css = "button[ng-click='back()']")
    public WebElement backToCustomerPageButton;

    @FindBy(css = "button[ng-click='withdrawl()']")
    public WebElement withdrawButton;

    @FindBy(css = "button[ng-click='reset()']")
    public WebElement resetTransactionsButton;

    private Select select;

    public Select chooseAccountNumber(String number) {
        select = new Select(selectElement);
        select.selectByVisibleText(number);
        return select;
    }

    public void createDeposit(String depositValue, WebDriver driver) { //TODO: add comments to waitElements
        buttonDeposit.click();
        WebElement waitInsertDepositValue = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[ng-model='amount']")));
        waitInsertDepositValue.sendKeys(depositValue);
        WebElement waitButtonConfirmDeposit = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("btn-default")));//TODO: refactor to good locator
        waitButtonConfirmDeposit.click();
    }

    public String currentBalanceValue(WebDriver driver) {
        WebElement waitBalanceValue = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div[2]/strong[2]"))); //TODO: refactor to new locator
        return waitBalanceValue.getText();
    }

    public boolean checkTransactionsIsExist() {
        try {
            return transactionItem.isDisplayed();
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public void createWithdraw(String withdrawValue, WebDriver driver) {
        withdrawButton.click();
        WebElement waitInputWithdraw = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='number']")));
        waitInputWithdraw.sendKeys(withdrawValue);

        WebElement waitButtonConfirmWithdraw = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/button")));
        waitButtonConfirmWithdraw.click();
    }

    public boolean checkWebElementExist(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException exception) {
            return false;
        }

    }
}
