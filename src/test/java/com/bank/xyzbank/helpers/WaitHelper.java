package com.bank.xyzbank.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Kreminskyi A.A. on окт., 2021
 */
public class WaitHelper {

    private WebDriver driver;
    private final Logger logger = LoggerFactory.getLogger(WaitHelper.class);

    public WaitHelper(WebDriver driver) {
        this.driver = driver;
        logger.info("* Wait helper created *");
    }

    public void setImplicitlyWait(long timeout, TimeUnit timeUnit) {
        driver.manage().timeouts().implicitlyWait(timeout, timeUnit);
    }

    public WebElement waitElementClickable(WebElement element, int timeout) {
        return new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitElementClickableAndClick(WebElement element, int timeout) {
        var waitElement = new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
        waitElement.click();
    }

    public void waitElementClickableWithClearAndSendText(WebElement element, int timeout, String text){
        var waitElement = new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
        waitElement.clear();
        waitElement.sendKeys(text);
    }
}
