package com.bank.xyzbank.pages;

import com.bank.xyzbank.helpers.SelectHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Kreminskyi A.A. on авг., 2021
 */
public class CustomerLoginPage extends BasePage {

    SelectHelper selectHelper;

    public CustomerLoginPage(WebDriver driver) {
        super(driver);
        selectHelper = new SelectHelper(driver);
    }

    @FindBy(css = "select[id='userSelect']")
    private WebElement selectLogin;

    @FindBy(xpath = "//*[text() = 'Login']")
    private WebElement loginButton;

    @FindBy(css = "span[class='fontBig ng-binding']")
    private WebElement loginName;

    @FindBy(css = "button[class='btn logout']")
    private WebElement logoutButton;


    public void chooseLoginNameAndLogin(String name) {
        selectHelper.selectByVisibleText(selectLogin, name);
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
