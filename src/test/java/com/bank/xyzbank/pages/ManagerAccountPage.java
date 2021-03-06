package com.bank.xyzbank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.stream.Collectors;

/**
 * Created by Kreminskyi A.A. on авг., 2021
 */
public class ManagerAccountPage extends BasePage {

    public ManagerAccountPage(WebDriver driver) {
        super(driver);
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

    @FindBy(css = "input[placeholder='Search Customer']")
    private WebElement searchInputCustomer;

    @FindBy(css = "td[class='ng-binding']")
    private WebElement customerLocator;

    @FindBy(xpath = "//*[text()='Process']")
    private WebElement buttonProcess;

    @FindBy(css = "select[id='userSelect']")
    private WebElement selectUser;

    @FindBy(css = "select[id='currency']")
    private WebElement selectCurrency;

    public boolean checkManagerLoginPage() { // TODO: Refactor to some elements or function for check
        logger.info("* Check buttons is displayed *");
        return buttonAddCustomer.isDisplayed() && buttonOpenAccount.isDisplayed() && buttonCustomers.isDisplayed();
    }

    public void createNewCustomer(String firstName, String lastName, String postCode) {
        logger.info("* Create new customer method *");
        buttonAddCustomer.click();
        waitHelper.waitElementClickableWithClearAndSendText(inputFirstName, 10, firstName);
        waitHelper.waitElementClickableWithClearAndSendText(inputLastName, 10, lastName);
        waitHelper.waitElementClickableWithClearAndSendText(inputPostCode, 10, postCode);
        waitHelper.waitElementClickableAndClick(addCustomerButton, 10);
    }

    public boolean acceptAlertCreateCustomer() {
        logger.info("* Accept alert create customer *");
        boolean checkAlert = alertHelper.getTextInAlert().contains("Customer added successfully");
        alertHelper.acceptAlertIfPresent();
        return checkAlert;
    }

    public boolean searchCreatedUser(String firstNameOrLastName) {
        logger.info("* Search created user *");
        buttonCustomers.click();
        waitHelper.waitElementClickableWithClearAndSendText(searchInputCustomer, 10, firstNameOrLastName);
        var foundCustomer = waitHelper.waitElementsVisibility(customerLocator, 10)
                .stream()
                .filter(listOfCustomers -> firstNameOrLastName.equals(listOfCustomers.getText()))
                .findAny()
                .orElse(null);
        return foundCustomer != null;
    }

    public void openAccountNumberForCustomer(String firstName, String lastName) {
        logger.info("* Open account number for customer *");
        openAccountButton.click();
        selectHelper.selectByVisibleText(selectUser, firstName + " " + lastName);
        selectHelper.selectByVisibleText(selectCurrency, "Dollar");
        waitHelper.waitElementClickableAndClick(buttonProcess, 10);
    }

    public String confirmAlertOpenAccountAndReturnIdAccount() {
        logger.info("* Confirm alert open account number and return id account *");
        var lengthAlert = alertHelper.getTextInAlert().length();
        String idAccountNumber = alertHelper.getTextInAlert().substring(lengthAlert - 4);
        alertHelper.acceptAlertIfPresent();
        return idAccountNumber;
    }

    public void deleteCustomer() {
        logger.info("* Delete customer *");
        waitHelper.waitElementClickable(deleteButton, 10).click();
    }

    public void goToHomePage() {
        logger.info("* Go to home page *");
        homeButton.click();
    }

}
