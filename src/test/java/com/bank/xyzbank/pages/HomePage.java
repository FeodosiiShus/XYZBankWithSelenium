package com.bank.xyzbank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "button[ng-click='customer()']")
    private WebElement customerButton;

    @FindBy(css = "button[ng-click='manager()']")
    private WebElement managerButton;

    public boolean isHomePage() {
        logger.info("* Check is home page method *");
        return customerButton.isDisplayed() && managerButton.isDisplayed();
    }

    public CustomerLoginPage goToCustomerLoginPage() {
        logger.info("* Go to customer login page method *");
        customerButton.click();
        return new CustomerLoginPage(driver);
    }

    public ManagerAccountPage goToManagerAccountPage() {
        logger.info("* Go to manager login page method *");
        managerButton.click();
        return new ManagerAccountPage(driver);
    }
}
