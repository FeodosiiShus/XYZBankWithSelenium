package com.bank.xyzbank.main;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class MainPageTest {

    private WebDriver driver;

    @BeforeEach
    public void initDriver() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void mainPageOpen() throws InterruptedException {
        var mainPage = new MainPage(driver);
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/");
        assertTrue(mainPage.checkMainPage());//TODO: delete assertion
        mainPage.clickOnCustomerLoginPage();
        mainPage.clickOnManagerLoginPage();
    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }
}
