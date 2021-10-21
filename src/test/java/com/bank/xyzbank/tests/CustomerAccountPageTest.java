package com.bank.xyzbank.tests;

import com.bank.xyzbank.helpers.PageUrls;
import com.bank.xyzbank.pages.CustomerAccountPage;
import com.bank.xyzbank.pages.CustomerLoginPage;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Kreminskyi A.A. on авг., 2021
 */
public class CustomerAccountPageTest {

    WebDriver driver;
    static final String accountNumberRupee = "1006";
    static final String accountNumberDollar = "1004";
    static final String accountNumberPound = "1005";
    static final String errorMessageWithdraw = "Transaction Failed. You can not withdraw amount more than the balance.";

    CustomerLoginPage customerLoginPage;
    CustomerAccountPage customerAccountPage;

    @BeforeEach
    public void initDriver() throws MalformedURLException {
        //ChromeOptions options = new ChromeOptions();
        //driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);

        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.get(PageUrls.HOME_PAGE);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        customerLoginPage = new CustomerLoginPage(driver);
        driver.get(PageUrls.LOGIN_CUSTOMER_PAGE);
        customerLoginPage.chooseLoginNameAndLogin("Harry Potter");
        customerAccountPage = new CustomerAccountPage(driver);
    }

    @Test
    public void checkAccountNumberRupee() {
        customerAccountPage.chooseAccountNumber(accountNumberRupee);
        assertTrue(customerAccountPage.isDisplayedCurrencyRupee());
        assertEquals(customerAccountPage.getAccountNumber(), accountNumberRupee);
    }

    @Test
    public void checkAccountNumberDollar() {
        customerAccountPage.chooseAccountNumber(accountNumberDollar);
        assertTrue(customerAccountPage.isDisplayedCurrencyDollar());
        assertEquals(customerAccountPage.getAccountNumber(), accountNumberDollar);
    }

    @Test
    public void checkAccountNumberPound() {
        customerAccountPage.chooseAccountNumber(accountNumberPound);
        assertTrue(customerAccountPage.isDisplayedCurrencyPound());
        assertEquals(customerAccountPage.getAccountNumber(), accountNumberPound);
    }

    @Test
    public void createDeposit() {
        String balanceVal = customerAccountPage.currentBalanceValue();
        customerAccountPage.createDeposit("100");
        assertNotEquals(balanceVal, customerAccountPage.currentBalanceValue());
        assertEquals("100", customerAccountPage.currentBalanceValue());
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
