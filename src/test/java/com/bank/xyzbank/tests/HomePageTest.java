package com.bank.xyzbank.tests;

import com.bank.xyzbank.factories.Browsers;
import com.bank.xyzbank.factories.ConfigFactory;
import com.bank.xyzbank.helpers.ImplicitlyWaitHelper;
import com.bank.xyzbank.helpers.PageUrls;
import com.bank.xyzbank.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class HomePageTest {

    private WebDriver driver;
    private HomePage homePage;
    PageUrls urls = ConfigFactory.createConfig(PageUrls.class);

    @BeforeEach
    public void initDriver() {
        driver = Browsers.CHROME_DOCKER.create();
        ImplicitlyWaitHelper.waitPage(driver, 10);
        homePage = new HomePage(driver);
        driver.get(urls.homePage());
    }

    @Test
    public void checkIsThisHomePage() {
        assertTrue(homePage.isHomePage());
    }

    @Test
    public void openHomePageGoToCustomerLoginPage() {
        assertTrue(homePage.isHomePage());
        homePage.goToCustomerLoginPage();
    }

    @Test
    public void openHomePageGoToManagerLoginPage() {
        assertTrue(homePage.isHomePage());
        homePage.goToManagerAccountPage();
    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }
}
