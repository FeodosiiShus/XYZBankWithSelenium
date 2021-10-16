package com.bank.xyzbank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Kreminskyi A.A. on авг., 2021
 */
public class CustomerLoginPage {

    public CustomerLoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "select[id='userSelect']")
    @CacheLookup
    WebElement select;

    private Select selectLoginName;

    @FindBy(xpath = "//*[text() = 'Login']")
    private WebElement loginButton;

    @FindBy(css = "span[class='fontBig ng-binding']")
    private WebElement loginName;

    @FindBy(css = "button[class='btn logout']")
    private WebElement logoutButton;


    public void chooseLoginNameAndLogin(String name) {
        selectLoginName = new Select(select);
        selectLoginName.selectByVisibleText(name);
        loginButton.click();
    }

    public boolean checkLoginButtonIsDisplayed() {
        return loginButton.isDisplayed();
    }

    public boolean checkLoginName(String name) {
        return loginName.getText().equals(name);
    }

    public void logout() {
        logoutButton.click();
    }
}