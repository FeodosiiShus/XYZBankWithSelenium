package com.bank.xyzbank.tests;

import com.bank.xyzbank.pages.CustomerLoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Kreminskyi A.A. on авг., 2021
 */
public class CustomerLoginPageTest {

    private WebDriver webDriver;

    @BeforeEach
    public void initDriver() {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void loginWithChooseName() {
        String name = "Harry Potter";
        var loginPage = new CustomerLoginPage(webDriver);
        webDriver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/customer");
        loginPage.chooseLoginNameAndLogin(name);
        assertTrue(loginPage.checkLoginButtonIsDisplayed());
        assertTrue(loginPage.checkLoginName(name));
        loginPage.logout();
    }

    @Test
    public void noLoginWithoutChooseName() {
        var loginPage = new CustomerLoginPage(webDriver);
        webDriver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/customer");
        assertFalse(loginPage.checkLoginButtonIsDisplayed());
    }

    @AfterEach
    public void closeDriver() {
        webDriver.quit();
    }
}

