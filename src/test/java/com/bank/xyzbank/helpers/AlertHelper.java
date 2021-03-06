package com.bank.xyzbank.helpers;

import com.codeborne.selenide.ex.AlertNotFoundException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

/**
 * Created by Kreminskyi A.A. on окт., 2021
 */
public class AlertHelper extends BaseHelper {

    public AlertHelper(WebDriver driver) {
        super(driver);
    }

    public Alert getAlert() {
        logger.info("* Get alert *");
        return driver.switchTo().alert();
    }

    public String getTextInAlert() {
        String alertText = driver.switchTo().alert().getText();
        logger.info("* Get text in alert: " + alertText + " *");
        return alertText;
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
        logger.info("* Alert accepted *");
    }

    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
        logger.info("* Alert dismissed");
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            logger.info("* Alert is present *");
            return true;
        } catch (AlertNotFoundException exception) {
            logger.info(String.valueOf(exception.getCause()));
            return false;
        }
    }

    public void acceptAlertIfPresent() {
        if (isAlertPresent()) {
            acceptAlert();
        } else {
            logger.info("* Alert is not present *");
        }
    }

    public void dismissAlertIfPresent() {
        if (isAlertPresent()) {
            dismissAlert();
        } else {
            logger.info("* Alert is not present *");
        }
    }

}
