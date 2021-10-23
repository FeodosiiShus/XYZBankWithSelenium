package com.bank.xyzbank.tests;

import com.bank.xyzbank.factories.Browsers;
import com.bank.xyzbank.factories.ConfigFactory;
import com.bank.xyzbank.helpers.PageUrls;
import com.bank.xyzbank.pages.CustomerLoginPage;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Kreminskyi A.A. on авг., 2021
 */
public class CustomerLoginPageTest {

    private WebDriver driver;
    PageUrls urls = ConfigFactory.createConfig(PageUrls.class);

    @BeforeEach
    public void initDriver() {
        driver = Browsers.CHROME_DOCKER.create();
        driver.get(urls.customerLoginPage());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void loginWithChooseName() {
        String name = "Harry Potter";
        var loginPage = new CustomerLoginPage(driver);
        loginPage.chooseLoginNameAndLogin(name);
        assertTrue(loginPage.checkLoginButtonIsDisplayed());
        assertTrue(loginPage.checkLoginName(name));
        loginPage.logout();
    }

    @Test
    public void noLoginWithoutChooseName() {
        var loginPage = new CustomerLoginPage(driver);
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/customer");
        assertFalse(loginPage.checkLoginButtonIsDisplayed());
    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }
}

