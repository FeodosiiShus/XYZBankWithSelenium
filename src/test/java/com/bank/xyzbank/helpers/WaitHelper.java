package com.bank.xyzbank.helpers;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Kreminskyi A.A. on окт., 2021
 */
public class WaitHelper {

    private WebDriver driver;

    public WaitHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void setImplicitlyWait(long timeout, TimeUnit timeUnit){
        driver.manage().timeouts().implicitlyWait(timeout, timeUnit);
    }
}
