package com.bank.xyzbank.pages;

import com.bank.xyzbank.helpers.AlertHelper;
import com.bank.xyzbank.helpers.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Kreminskyi A.A. on авг., 2021
 */
public class ManagerLoginPage {

    AlertHelper alertHelper;
    WaitHelper waitHelper;

    private final Logger logger = LoggerFactory.getLogger(ManagerLoginPage.class);

    public ManagerLoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        logger.info("* Create manager login page *");
        waitHelper = new WaitHelper(driver);
    }

    @FindBy(css = "button[ng-class='btnClass1']")
    private WebElement buttonAddCustomer;

    @FindBy(css = "button[ng-class='btnClass2']")
    private WebElement buttonOpenAccount;

    @FindBy(css = "button[ng-class='btnClass3']")
    private WebElement buttonCustomers;

    @FindBy(css = "button[ng-click='home()']")
    private WebElement homeButton;

    @FindBy(css = "button[ng-click='openAccount()']")
    private WebElement openAccountButton;

    @FindBy(css = "button[ng-click='deleteCust(cust)']")
    private WebElement deleteButton;

    @FindBy(css = "input[placeholder='First Name']")
    private WebElement inputFirstName;

    @FindBy(css = "input[placeholder='Last Name']")
    private WebElement inputLastName;

    @FindBy(css = "input[placeholder='Post Code']")
    private WebElement inputPostCode;

    @FindBy(xpath = "//*[text()='Add Customer']")
    private WebElement addCustomerButton;

    public boolean checkManagerLoginPage() { // TODO: Refactor to some elements or function for check
        return buttonAddCustomer.isDisplayed() && buttonOpenAccount.isDisplayed() && buttonCustomers.isDisplayed();
    }

    public void createNewCustomer(String firstName, String lastName, String postCode) {
        buttonAddCustomer.click();
        waitHelper.waitElementClickableWithClearAndSendText(inputFirstName, 10, firstName);
        waitHelper.waitElementClickableWithClearAndSendText(inputLastName, 10, lastName);
        waitHelper.waitElementClickableWithClearAndSendText(inputPostCode, 10, postCode);
        waitHelper.waitElementClickableAndClick(addCustomerButton, 10);
    }

    public boolean acceptAlertCreateCustomer(WebDriver driver) {
        alertHelper = new AlertHelper(driver);
        boolean checkAlert = alertHelper.getTextInAlert().contains("Customer added successfully");
        alertHelper.acceptAlertIfPresent();
        return checkAlert;
    }

    public boolean searchCreatedUser(WebDriver driver, String firstNameOrLastName) {
        buttonCustomers.click();
        var waitSearchInput = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[placeholder='Search Customer']")));
        waitSearchInput.clear();
        waitSearchInput.sendKeys(firstNameOrLastName);
        var listOfCustomers = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("td[class='ng-binding']")));
        var foundCustomer = listOfCustomers.parallelStream().filter(listOfCustomer -> firstNameOrLastName.equals(listOfCustomer.getText()))
                .findAny()
                .orElse(null); // TODO: check orElse
        return foundCustomer != null;
    }

    public void openAccountNumberForCustomer(WebDriver driver, String firstName, String lastName) {
        openAccountButton.click();
        var waitSelectCustomer = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("select[id='userSelect']")));
        var selectCustomer = new Select(waitSelectCustomer);
        selectCustomer.selectByVisibleText(firstName + " " + lastName);
        var waitSelectCurrency = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("select[id='currency']")));
        var selectCurrencyForCustomer = new Select(waitSelectCurrency);
        selectCurrencyForCustomer.selectByVisibleText("Dollar");
        var waitButtonProcess = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Process']")));
        waitButtonProcess.click();
    }

    public String confirmAlertOpenAccountAndReturnIdAccount(WebDriver driver) {
        var waitAlertOpenAccount = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.alertIsPresent());
        var lengthAlert = waitAlertOpenAccount.getText().length();
        String idAccountNumber = waitAlertOpenAccount.getText().substring(lengthAlert - 4);
        waitAlertOpenAccount.accept();
        return idAccountNumber;
    }

    public void deleteCustomer() {
        waitHelper.waitElementClickable(deleteButton, 10).click();
    }

    public void goToHomePage() {
        homeButton.click();
    }

}
