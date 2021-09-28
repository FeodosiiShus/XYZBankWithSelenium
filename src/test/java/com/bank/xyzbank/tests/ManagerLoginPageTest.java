package com.bank.xyzbank.tests;

import com.bank.xyzbank.pages.LoginPage;
import com.bank.xyzbank.pages.MainPage;
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

    private WebDriver driver;

    ManagerLoginPage managerLoginPage;
    MainPage mainPage;

    @BeforeEach
    public void initDriver() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        mainPage = new MainPage(driver);
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/");
        mainPage.clickOnManagerLoginPage();
    }

    @Test
    public void checkManagerLoginPageIsOpen() {
        managerLoginPage = new ManagerLoginPage(driver);
        assertTrue(managerLoginPage.checkManagerLoginPage());
    }

    @Test
    public void createNewCustomerAndCheckInCustomers() {
        managerLoginPage = new ManagerLoginPage(driver);
        managerLoginPage.createNewCustomer(driver, "Test", "Test", "100");
        assertTrue(managerLoginPage.checkAlertCreateCustomer(driver));
        assertTrue(managerLoginPage.searchCreatedUser(driver, "Test"));
    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }

}