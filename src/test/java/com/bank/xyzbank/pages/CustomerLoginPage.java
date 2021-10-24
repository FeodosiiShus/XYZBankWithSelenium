package com.bank.xyzbank.pages;

import org.openqa.selenium.StaleElementReferenceException;
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

    public CustomerAccountPage chooseLoginNameAndLogin(String name) {
        logger.info("* Choose login name and click log in *");
        selectHelper.selectByVisibleText(selectLogin, name);
        loginButton.click();
        return new CustomerAccountPage(driver);
    }

    public void chooseLoginName(String name) {
        logger.info("* Choose login name *");
        selectHelper.selectByVisibleText(selectLogin, name);
    }

    public boolean checkLoginButtonIsDisplayed() {
        logger.info("* Check login button is displayed *");
        return loginButton.isDisplayed();
    }

    public boolean checkLoginNameIsExist(String name) {
        logger.info("* Check login name exist in select element *");
        try {
            selectHelper.selectByVisibleText(selectLogin, name);
            return true;
        } catch (StaleElementReferenceException exception) {
            logger.info("! No such element exception: " + exception.getMessage());
            return false;
        }
    }
}
