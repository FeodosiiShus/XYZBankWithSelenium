package com.bank.xyzbank.tests;

import com.bank.xyzbank.factories.Browsers;
import com.bank.xyzbank.factories.ConfigFactory;
import com.bank.xyzbank.helpers.ImplicitlyWaitHelper;
import com.bank.xyzbank.helpers.PageUrls;
import com.bank.xyzbank.pages.CustomerLoginPage;
import com.bank.xyzbank.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Kreminskyi A.A. on авг., 2021
 */
public class CustomerLoginPageTest {

    private WebDriver driver;
    PageUrls urls = ConfigFactory.createConfig(PageUrls.class);

    HomePage homePage;
    CustomerLoginPage customerLoginPage;

    @BeforeEach
    public void initDriver() {
        driver = Browsers.CHROME_DOCKER.create();
        homePage = new HomePage(driver);
        driver.get(urls.homePage());
        ImplicitlyWaitHelper.waitPage(driver, 10);
        customerLoginPage = homePage.goToCustomerLoginPage();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hermoine Granger", "Harry Potter", "Ron Weasly", "Albus Dumbledore", "Neville Longbottom"})
    public void loginWithChooseName(String name) {
        customerLoginPage.chooseLoginNameAndLogin(name);
        assertTrue(customerLoginPage.checkLoginButtonIsDisplayed());
        assertTrue(customerLoginPage.checkLoginName(name));
        customerLoginPage.logout();
    }

    @Test
    public void noLoginWithoutChooseName() {
        assertFalse(customerLoginPage.checkLoginButtonIsDisplayed());
    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }
}

