package com.bank.xyzbank.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Created by Kreminskyi A.A. on окт., 2021
 */
public class WaitHelper extends BaseHelper {

    public WaitHelper(WebDriver driver) {
        super(driver);
    }

    public void setImplicitlyWait(long timeout) {
        logger.info("* start implicitly wait *");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
    }

    public WebElement waitElementClickable(WebElement element, long timeout) {
        logger.info("* wait element to be clickable *");
        return new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitElementClickableAndClick(WebElement element, int timeout) {
        logger.info("* wait element to be clickable and click *");
        var waitElement = new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(element));
        waitElement.click();
    }

    public void waitElementClickableWithClearAndSendText(WebElement element, int timeout, String text) {
        logger.info("* wait element to be clickable, clear field and insert text *");
        var waitElement = new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(element));
        waitElement.clear();
        waitElement.sendKeys(text);
    }

    public List<WebElement> waitElementsVisibility(WebElement elementLocator, int timeout) {
        logger.info("* wait element to be visibility *");
        return new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOfAllElements(elementLocator));
    }
}
