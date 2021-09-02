package com.bank.xyzbank.tests;

import com.bank.xyzbank.pages.ManagerLoginPage;
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
class ManagerLoginPageTest {

    private WebDriver webDriver;

    @BeforeEach
    public void initDriver(){
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void openManagerLoginPage(){
        var managerLoginPage = new ManagerLoginPage(webDriver);
        webDriver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager");
        assertTrue(managerLoginPage.checkManagerLoginPage());
    }

    @AfterEach
    public void closeDriver(){
        webDriver.quit();
    }

}