package com.bank.xyzbank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Kreminskyi A.A. on авг., 2021
 */
public class CustomerLoginPage extends BasePage {

    public CustomerLoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "select[id='userSelect']")
    private WebElement selectLogin;

    @FindBy(xpath = "//*[text() = 'Login']")
    private WebElement loginButton;

    @FindBy(css = "span[class='fontBig ng-binding']")
    private WebElement loginName;

    @FindBy(css = "button[class='btn logout']")
    private WebElement logoutButton;


    public CustomerAccountPage chooseLoginNameAndLogin(String name) {
        logger.info("* Choose login name and click log in *");
        selectHelper.selectByVisibleText(selectLogin, name);
        loginButton.click();
        return new CustomerAccountPage(driver);
    }

    public boolean checkLoginButtonIsDisplayed() {
        logger.info("* Check login button is displayed *");
        return loginButton.isDisplayed();
    }

    public boolean checkLoginName(String name) {
        logger.info("* Check login name equals attribute name *");
        return loginName.getText().equals(name);
    }

    public void logout() {
        logger.info("* Logout button click *");
        logoutButton.click();
    }
}
