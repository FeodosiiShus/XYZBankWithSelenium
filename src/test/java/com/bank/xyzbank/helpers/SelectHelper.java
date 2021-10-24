package com.bank.xyzbank.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Kreminskyi A.A. on окт., 2021
 */
public class SelectHelper extends BaseHelper {

    public SelectHelper(WebDriver driver) {
        super(driver);
    }

    public void selectByVisibleText(WebElement element, String text){
        logger.info("* Select value by visible text *");
        var select = new Select(element);
        select.selectByVisibleText(text);
    }
}
