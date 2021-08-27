package com.bank.xyzbank;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage implements Page {

    @FindBy(css = "button[ng-click='customer()']")
    WebElement customerButton;

    @FindBy(css = "button[ng-click='manager()']")
    WebElement managerButton;

    @Override
    public void init(final WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public boolean checkMainPage() {
        return customerButton.isDisplayed() && managerButton.isDisplayed();
    }
}
