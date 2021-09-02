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
    }

    @Test
    public void checkAccountNumber() {
        loginPage = new LoginPage(driver);
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/customer");
        loginPage.chooseLoginName("Harry Potter");
        loginPage.pressButtonLogin();
        customerAccountPage = new CustomerAccountPage(driver);
        customerAccountPage.chooseAccountNumber(accountNumber);
        assertTrue(customerAccountPage.currencyRupee.isDisplayed());
        assertFalse(customerAccountPage.checkWebElementExist(customerAccountPage.currencyPound));
        assertFalse(customerAccountPage.checkWebElementExist(customerAccountPage.currencyDollar));
        assertEquals(customerAccountPage.accountNumberValue.getText(), accountNumber);

    }


    @AfterEach
    public void closeDriver() {
        driver.quit();
    }
}
