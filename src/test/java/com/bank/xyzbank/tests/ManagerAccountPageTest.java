package com.bank.xyzbank.tests;

import com.bank.xyzbank.factories.Browsers;
import com.bank.xyzbank.factories.ConfigFactory;
import com.bank.xyzbank.helpers.ImplicitlyWaitHelper;
import com.bank.xyzbank.helpers.PageUrls;
import com.bank.xyzbank.pages.CustomerAccountPage;
import com.bank.xyzbank.pages.CustomerLoginPage;
import com.bank.xyzbank.pages.HomePage;
import com.bank.xyzbank.pages.ManagerAccountPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Kreminskyi A.A. on авг., 2021
 */
class ManagerAccountPageTest {

    private WebDriver driver;
    PageUrls urls = ConfigFactory.createConfig(PageUrls.class);

    ManagerAccountPage managerAccountPage;
    HomePage homePage;
    CustomerLoginPage customerLoginPage;
    CustomerAccountPage customerAccountPage;


    @BeforeEach
    public void initDriver() {
        driver = Browsers.CHROME_DOCKER.create();
        ImplicitlyWaitHelper.waitPage(driver, 10);
        homePage = new HomePage(driver);
        driver.get(urls.homePage());
        managerAccountPage = homePage.goToManagerAccountPage();
    }

    @Test
    public void checkManagerLoginPageIsOpen() {
        assertTrue(managerAccountPage.checkManagerLoginPage());
    }

    @Test
    public void createNewCustomerAndCheckInCustomers() {
        managerAccountPage.createNewCustomer("Test", "Test", "100");
        assertTrue(managerAccountPage.acceptAlertCreateCustomer());
        assertTrue(managerAccountPage.searchCreatedUser("Test"));
    }

    @Test
    public void createNewCustomerAndCheckInCustomerPage() {
        managerAccountPage.createNewCustomer("Test", "Test", "100");
        assertTrue(managerAccountPage.acceptAlertCreateCustomer());
        managerAccountPage.goToHomePage();
        homePage.goToCustomerLoginPage();
        customerLoginPage = new CustomerLoginPage(driver);
        customerLoginPage.chooseLoginNameAndLogin("Test Test");
        customerAccountPage = new CustomerAccountPage(driver);
        assertEquals("Test Test", customerAccountPage.getNameOfCurrentAccount());
    }

    @Test
    public void createNewCustomerAndOpenAccountForHim() {
        managerAccountPage.createNewCustomer("Test", "Test", "100");
        assertTrue(managerAccountPage.acceptAlertCreateCustomer());
        managerAccountPage.openAccountNumberForCustomer("Test", "Test");
        var idAccount = managerAccountPage.confirmAlertOpenAccountAndReturnIdAccount();
        managerAccountPage.goToHomePage();
        homePage.goToCustomerLoginPage();
        customerLoginPage = new CustomerLoginPage(driver);
        customerLoginPage.chooseLoginNameAndLogin("Test Test");
        customerAccountPage = new CustomerAccountPage(driver);
        customerAccountPage.chooseAccountNumber(idAccount);
    }

    @Test
    public void createNewCustomerAndDelete() {
        managerAccountPage.createNewCustomer("Test", "Test", "100");
        assertTrue(managerAccountPage.acceptAlertCreateCustomer());
        assertTrue(managerAccountPage.searchCreatedUser("Test"));
        managerAccountPage.deleteCustomer();
    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }

}