package com.bank.xyzbank.tests;

import com.bank.xyzbank.pages.CustomerAccountPage;
import com.bank.xyzbank.pages.LoginPage;
import com.bank.xyzbank.pages.MainPage;
import com.bank.xyzbank.pages.ManagerLoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Kreminskyi A.A. on авг., 2021
 */
class ManagerLoginPageTest {

    private WebDriver driver;

    ManagerLoginPage managerLoginPage;
    MainPage mainPage;
    LoginPage loginPage;
    CustomerAccountPage customerAccountPage;

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
        assertTrue(managerLoginPage.acceptAlertCreateCustomer(driver));
        assertTrue(managerLoginPage.searchCreatedUser(driver, "Test"));
    }

    @Test
    public void createNewCustomerAndCheckInCustomerPage() {
        managerLoginPage = new ManagerLoginPage(driver);
        managerLoginPage.createNewCustomer(driver, "Test", "Test", "100");
        assertTrue(managerLoginPage.acceptAlertCreateCustomer(driver));
        managerLoginPage.goToHomePage();
        mainPage.goToCustomerLoginPage();
        loginPage = new LoginPage(driver);
        loginPage.chooseLoginNameAndLogin("Test Test");
        customerAccountPage = new CustomerAccountPage(driver);
        assertEquals("Test Test", customerAccountPage.getNameOfCurrentAccount());
    }

    @Test
    public void createNewCustomerAndOpenAccountForHim() {
        managerLoginPage = new ManagerLoginPage(driver);
        managerLoginPage.createNewCustomer(driver, "Test", "Test", "100");
        assertTrue(managerLoginPage.acceptAlertCreateCustomer(driver));
        managerLoginPage.openAccountNumberForCustomer(driver, "Test", "Test");
        var idAccount = managerLoginPage.confirmAlertOpenAccountAndReturnIdAccount(driver);
        managerLoginPage.goToHomePage();
        mainPage.goToCustomerLoginPage();
        loginPage = new LoginPage(driver);
        loginPage.chooseLoginNameAndLogin("Test Test");
        customerAccountPage = new CustomerAccountPage(driver);
        customerAccountPage.chooseAccountNumber(idAccount);
    }

    @Test
    public void createNewCustomerAndDelete() {
        managerLoginPage = new ManagerLoginPage(driver);
        managerLoginPage.createNewCustomer(driver, "Test", "Test", "100");
        assertTrue(managerLoginPage.acceptAlertCreateCustomer(driver));
        assertTrue(managerLoginPage.searchCreatedUser(driver, "Test"));
        managerLoginPage.deleteCustomer(driver);
    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }

}