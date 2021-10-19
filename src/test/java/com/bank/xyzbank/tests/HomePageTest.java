package com.bank.xyzbank.tests;

import com.bank.xyzbank.helpers.PageUrls;
import com.bank.xyzbank.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class HomePageTest {

    private WebDriver driver;
    private HomePage homePage;

    @BeforeEach
    public void initDriver() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homePage = new HomePage(driver);
        driver.get(PageUrls.HOME_PAGE);
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
        homePage.goToManagerLoginPage();
    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }
}
