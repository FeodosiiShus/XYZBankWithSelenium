package com.bank.xyzbank.pages;

import com.bank.xyzbank.helpers.AlertHelper;
import com.bank.xyzbank.helpers.SelectHelper;
import com.bank.xyzbank.helpers.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Kreminskyi A.A. on окт., 2021
 */
public class BasePage {
    protected WebDriver driver;

    protected WaitHelper waitHelper;
    protected SelectHelper selectHelper;
    protected AlertHelper alertHelper;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        logger.info("* Create " +getClass().getSimpleName() + " page *");
        waitHelper = new WaitHelper(driver);
        selectHelper = new SelectHelper(driver);
        alertHelper = new AlertHelper(driver);
    }
}
