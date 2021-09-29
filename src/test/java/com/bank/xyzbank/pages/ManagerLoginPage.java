package com.bank.xyzbank.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Kreminskyi A.A. on авг., 2021
 */
public class ManagerLoginPage {

    public ManagerLoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[ng-class='btnClass1']")
    public WebElement buttonAddCustomer;

    @FindBy(css = "button[ng-class='btnClass2']")
    public WebElement buttonOpenAccount;

    @FindBy(css = "button[ng-class='btnClass3']")
    public WebElement buttonCustomers;

    @FindBy(css = "button[ng-click='home()']")
    public WebElement homeButton;


    public boolean checkManagerLoginPage() { // TODO: Refactor to some elements or function for check
        return buttonAddCustomer.isDisplayed() && buttonOpenAccount.isDisplayed() && buttonCustomers.isDisplayed();
    }

    public void createNewCustomer(WebDriver driver, String firstName, String lastName, String postCode) {
        buttonAddCustomer.click();
        var waitFirstNameInsert = (new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[placeholder='First Name']"))));
        waitFirstNameInsert.sendKeys(firstName);
        var waitLastNameInsert = (new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[placeholder='Last Name']"))));
        waitLastNameInsert.sendKeys(lastName);
        var waitPostCodeInsert = (new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[placeholder='Post Code']"))));
        waitPostCodeInsert.sendKeys(postCode);
        var waitAddCustomerButton = (new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Add Customer']"))));
        waitAddCustomerButton.click();
    }

    public boolean checkAlertCreateCustomer(WebDriver driver) {
        var waitAlertCreateCustomer = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.alertIsPresent());
        boolean checkAlert = waitAlertCreateCustomer.getText().contains("Customer added successfully");
        waitAlertCreateCustomer.accept();
        return checkAlert;
    }

    public boolean searchCreatedUser(WebDriver driver, String searchCustomer) {
        buttonCustomers.click();
        var waitSearchInput = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[placeholder='Search Customer']")));
        waitSearchInput.sendKeys(searchCustomer);
        var listOfCustomers = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("td[class='ng-binding']")));
        var foundCustomer = listOfCustomers.stream().parallel().filter(listOfCustomer -> searchCustomer.equals(listOfCustomer.getText()))
                .findAny()
                .orElse(null);
        return foundCustomer != null;
    }

    public void goToHomePage(){
        homeButton.click();
    }

}
