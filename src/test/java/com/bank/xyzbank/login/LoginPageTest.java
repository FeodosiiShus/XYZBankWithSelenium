package com.bank.xyzbank.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Kreminskyi A.A. on авг., 2021
 */
public class LoginPageTest {

    private WebDriver webDriver;

    public String name = "Harry Potter";

    @BeforeEach
    public void initDriver(){
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void loginWithChooseName(){
        var loginPage = new LoginPage();
        loginPage.init(webDriver);
        webDriver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/customer");
        loginPage.chooseLoginName(name);
        loginPage.pressButtonLogin();
        assertTrue(loginPage.checkLoginName(name));
        loginPage.logout();
    }

    @AfterEach
    public void closeDriver(){
        webDriver.quit();
    }
}
