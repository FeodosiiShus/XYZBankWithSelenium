package com.bank.xyzbank.login.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by Kreminskyi A.A. on авг., 2021
 */
public class ManagerLoginPage {

    public ManagerLoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[ng-click='manager()']")
    private WebElement managerLoginButton;

    @FindAll(@FindBy(css = "center"))
    List<WebElement> listOfButtons;

    public void openManagerLoginPage() {
        managerLoginButton.click();
    }

}
