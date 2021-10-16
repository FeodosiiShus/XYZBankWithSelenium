package com.bank.xyzbank.tests;

import com.bank.xyzbank.helpers.PageUrls;
import com.bank.xyzbank.pages.CustomerAccountPage;
import com.bank.xyzbank.pages.CustomerLoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Kreminskyi A.A. on авг., 2021
 */
public class CustomerAccountPageTest {

    WebDriver driver;
    String accountNumber = "1006";

    CustomerLoginPage customerLoginPage;
    CustomerAccountPage customerAccountPage;

    @BeforeEach
    public void initDriver() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        customerLoginPage = new CustomerLoginPage(driver);
        driver.get(PageUrls.LOGIN_CUSTOMER_PAGE);
        customerLoginPage.chooseLoginNameAndLogin("Harry Potter");
        customerAccountPage = new CustomerAccountPage(driver);
    }

    @Test
    public void checkAccountNumber() {
        customerAccountPage.chooseAccountNumber(accountNumber);
        assertTrue(customerAccountPage.isDisplayedCurrencyRupee());
        //assertFalse(customerAccountPage.checkWebElementExist(customerAccountPage.currencyPound));
        //assertFalse(customerAccountPage.checkWebElementExist(customerAccountPage.currencyDollar));
        assertEquals(customerAccountPage.getAccountNumber(), accountNumber);
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
        customerAccountPage.backToCustomerTab();
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

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }
}
