package com.bank.xyzbank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Kreminskyi A.A. on авг., 2021
 */
public class ManagerLoginPage {

    public ManagerLoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[ng-class='btnClass1']")
    private WebElement buttonAddCustomer;

    @FindBy(css = "button[ng-class='btnClass2']")
    private WebElement buttonOpenAccount;

    @FindBy(css = "button[ng-class='btnClass3']")
    private WebElement buttonCustomers;


    public boolean checkManagerLoginPage() {
        return buttonAddCustomer.isDisplayed() && buttonOpenAccount.isDisplayed() && buttonCustomers.isDisplayed();
    }

}
