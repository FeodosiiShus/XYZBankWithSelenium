package com.bank.xyzbank.tests;

import com.bank.xyzbank.helpers.PageUrls;
import com.bank.xyzbank.pages.CustomerAccountPage;
import com.bank.xyzbank.pages.CustomerLoginPage;
import com.bank.xyzbank.pages.HomePage;
import com.bank.xyzbank.pages.ManagerLoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Kreminskyi A.A. on авг., 2021
 */
class ManagerCustomerLoginPageTest {

    private WebDriver driver;

    ManagerLoginPage managerLoginPage;
    HomePage homePage;
    CustomerLoginPage customerLoginPage;
    CustomerAccountPage customerAccountPage;

    @BeforeEach
    public void initDriver() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homePage = new HomePage(driver);
        driver.get(PageUrls.HOME_PAGE);
        homePage.goToManagerLoginPage();
    }

    @Test
    public void checkManagerLoginPageIsOpen() {
        managerLoginPage = new ManagerLoginPage(driver);
        assertTrue(managerLoginPage.checkManagerLoginPage());
    }

    @Test
    public void createNewCustomerAndCheckInCustomers() {
        managerLoginPage = new ManagerLoginPage(driver);
        managerLoginPage.createNewCustomer("Test", "Test", "100");
        assertTrue(managerLoginPage.acceptAlertCreateCustomer(driver));
        assertTrue(managerLoginPage.searchCreatedUser(driver, "Test"));
    }

    @Test
    public void createNewCustomerAndCheckInCustomerPage() {
        managerLoginPage = new ManagerLoginPage(driver);
        managerLoginPage.createNewCustomer("Test", "Test", "100");
        assertTrue(managerLoginPage.acceptAlertCreateCustomer(driver));
        managerLoginPage.goToHomePage();
        homePage.goToCustomerLoginPage();
        customerLoginPage = new CustomerLoginPage(driver);
        customerLoginPage.chooseLoginNameAndLogin("Test Test");
        customerAccountPage = new CustomerAccountPage(driver);
        assertEquals("Test Test", customerAccountPage.getNameOfCurrentAccount());
    }

    @Test
    public void createNewCustomerAndOpenAccountForHim() {
        managerLoginPage = new ManagerLoginPage(driver);
        managerLoginPage.createNewCustomer("Test", "Test", "100");
        assertTrue(managerLoginPage.acceptAlertCreateCustomer(driver));
        managerLoginPage.openAccountNumberForCustomer(driver, "Test", "Test");
        var idAccount = managerLoginPage.confirmAlertOpenAccountAndReturnIdAccount(driver);
        managerLoginPage.goToHomePage();
        homePage.goToCustomerLoginPage();
        customerLoginPage = new CustomerLoginPage(driver);
        customerLoginPage.chooseLoginNameAndLogin("Test Test");
        customerAccountPage = new CustomerAccountPage(driver);
        customerAccountPage.chooseAccountNumber(idAccount);
    }

    @Test
    public void createNewCustomerAndDelete() {
        managerLoginPage = new ManagerLoginPage(driver);
        managerLoginPage.createNewCustomer("Test", "Test", "100");
        assertTrue(managerLoginPage.acceptAlertCreateCustomer(driver));
        assertTrue(managerLoginPage.searchCreatedUser(driver, "Test"));
        managerLoginPage.deleteCustomer();
    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }

}