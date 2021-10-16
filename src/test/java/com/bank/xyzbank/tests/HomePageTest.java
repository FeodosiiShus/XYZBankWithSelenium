package com.bank.xyzbank.tests;

import com.bank.xyzbank.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class HomePageTest {

    private WebDriver driver;

    @BeforeEach
    public void initDriver() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void homePageOpen() {
        var homePage = new HomePage(driver);
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/");
        assertTrue(homePage.isHomePage());
        homePage.goToCustomerLoginPage();
        homePage.goToManagerLoginPage();
    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }
}
