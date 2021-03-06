package com.bank.xyzbank.tests;

import com.bank.xyzbank.factories.Browsers;
import com.bank.xyzbank.factories.ConfigFactory;
import com.bank.xyzbank.helpers.ImplicitlyWaitHelper;
import com.bank.xyzbank.helpers.PageUrls;
import com.bank.xyzbank.pages.CustomerAccountPage;
import com.bank.xyzbank.pages.CustomerLoginPage;
import com.bank.xyzbank.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Kreminskyi A.A. on авг., 2021
 */
public class CustomerAccountPageTest {

    WebDriver driver;
    PageUrls urls = ConfigFactory.createConfig(PageUrls.class);

    static final String accountName = "Harry Potter";
    static final String errorMessageWithdraw = "Transaction Failed. You can not withdraw amount more than the balance.";

    HomePage homePage;
    CustomerLoginPage customerLoginPage;
    CustomerAccountPage customerAccountPage;

    @BeforeEach
    public void initDriver() {
        driver = Browsers.CHROME_DOCKER.create();
        homePage = new HomePage(driver);
        driver.get(urls.homePage());
        ImplicitlyWaitHelper.waitPage(driver, 10);
        customerLoginPage = homePage.goToCustomerLoginPage();
        customerAccountPage = customerLoginPage.chooseLoginNameAndLogin(accountName);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1004", "1005", "1006"})
    void checkAccountNumber(String accountNumber) {
        customerAccountPage.chooseAccountNumber(accountNumber);
        assertEquals(customerAccountPage.getAccountNumber(), accountNumber);
    }

    @ParameterizedTest
    @ValueSource(strings = {"100", "2000"})
    public void createDeposit(String depositValue) {
        customerAccountPage.goToTransactionTab();
        customerAccountPage.deleteAllTransactions();
        customerAccountPage.goToCustomerTab();
        customerAccountPage.createDeposit(depositValue);
        assertEquals(depositValue, customerAccountPage.currentBalanceValue());
        assertTrue(customerAccountPage.isDepositSuccessful());
    }

    @Test
    public void checkTransactionIsExists() {
        customerAccountPage.goToTransactionTab();
        assertFalse(customerAccountPage.checkTransactionsIsExist());
        customerAccountPage.goToCustomerTab();
        customerAccountPage.createDeposit("200");
        driver.navigate().refresh();
        customerAccountPage.goToTransactionTab();
        driver.navigate().refresh();
        assertTrue(customerAccountPage.checkTransactionsIsExist());
    }

    @Test
    public void createDepositAndWithdraw() {
        customerAccountPage.createDeposit("200");
        driver.navigate().refresh();
        customerAccountPage.createWithdraw("200");
        assertEquals("0", customerAccountPage.currentBalanceValue());
    }

    @Test
    void createWithdrawWithZeroBalance() {
        customerAccountPage.goToTransactionTab();
        customerAccountPage.deleteAllTransactions();
        customerAccountPage.goToCustomerTab();
        customerAccountPage.createWithdraw("100");
        assertTrue(customerAccountPage.isErrorMessageDisplayed());
        assertEquals(customerAccountPage.getErrorMessageWithdraw(), errorMessageWithdraw);
    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }
}
