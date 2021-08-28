package com.bank.xyzbank.main;

import com.bank.xyzbank.login.customer.LoginPage;
import com.bank.xyzbank.login.manager.ManagerLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[ng-click='customer()']")
    private WebElement customerButton;

    @FindBy(css = "button[ng-click='manager()']")
    private WebElement managerButton;

    public boolean checkMainPage() {
        return customerButton.isDisplayed() && managerButton.isDisplayed();
    }

    public LoginPage clickOnCustomerLoginPage() {
        customerButton.click();
        return new LoginPage(driver);
    }

    public ManagerLoginPage clickOnManagerLoginPage() {
        managerButton.click();
        return new ManagerLoginPage(driver);
    }
}
