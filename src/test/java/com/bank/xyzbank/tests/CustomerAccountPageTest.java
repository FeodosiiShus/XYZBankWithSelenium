package com.bank.xyzbank.tests;

import com.bank.xyzbank.pages.CustomerAccountPage;
import com.bank.xyzbank.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Kreminskyi A.A. on авг., 2021
 */
public class CustomerAccountPageTest {

    WebDriver driver;
    String accountNumber = "1006";

    LoginPage loginPage;
    CustomerAccountPage customerAccountPage;

    @BeforeEach
    public void initDriver() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver);
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/customer");
        loginPage.chooseLoginName("Harry Potter");
        loginPage.pressButtonLogin();
        customerAccountPage = new CustomerAccountPage(driver);
    }

    @Test
    public void checkAccountNumber() {
        customerAccountPage.chooseAccountNumber(accountNumber);
        assertTrue(customerAccountPage.currencyRupee.isDisplayed());
        assertFalse(customerAccountPage.checkWebElementExist(customerAccountPage.currencyPound));
        assertFalse(customerAccountPage.checkWebElementExist(customerAccountPage.currencyDollar));
        assertEquals(customerAccountPage.accountNumberValue.getText(), accountNumber);
    }

    @Test
    public void createDeposit() {
        String balanceVal = customerAccountPage.balanceValue.getText();
        customerAccountPage.createDeposit("100", driver);
        assertNotEquals(balanceVal, customerAccountPage.balanceValue.getText());
        assertEquals("100", customerAccountPage.currentBalanceValue(driver));
    }

    @Test
    public void checkTransactionIsExists() {
        customerAccountPage.goToTransactions.click();
        assertFalse(customerAccountPage.checkTransactionsIsExist());
        customerAccountPage.backToCustomerPageButton.click();
        customerAccountPage.createDeposit("200", driver);
        driver.navigate().refresh();
        customerAccountPage.goToTransactions.click();
        driver.navigate().refresh();
        assertTrue(customerAccountPage.checkTransactionsIsExist());
    }

    @Test
    public void createDepositAndWithdraw() {
        customerAccountPage.createDeposit("200", driver);
        driver.navigate().refresh();
        customerAccountPage.createWithdraw("200", driver);
        assertEquals("0", customerAccountPage.currentBalanceValue(driver));
    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }
}
