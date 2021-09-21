package com.bank.xyzbank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div/div[2]/div/div[2]/strong[2]"))); //TODO: refactor to new locator
        return waitBalanceValue.getText();
    }

    public boolean checkTransactionsIsExist(WebDriver driver) {
        try {
            WebElement waitTransactionItem = new WebDriverWait(driver, 3)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td[class=ng-binding]")));
            return waitTransactionItem.isDisplayed();
        } catch (NoSuchElementException exception) {
            return false;
        }
    }


    public boolean checkWebElementExist(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException exception) {
            return false;
        }

    }
}
