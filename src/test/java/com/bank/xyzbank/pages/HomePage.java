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
        return customerButton.isDisplayed() && managerButton.isDisplayed();
    }

    public CustomerLoginPage goToCustomerLoginPage() {
        customerButton.click();
        return new CustomerLoginPage(driver);
    }

    public ManagerLoginPage goToManagerLoginPage() {
        managerButton.click();
        return new ManagerLoginPage(driver);
    }
}
