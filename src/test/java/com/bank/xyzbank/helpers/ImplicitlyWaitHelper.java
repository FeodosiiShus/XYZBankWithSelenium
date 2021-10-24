package com.bank.xyzbank.helpers;

import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class ImplicitlyWaitHelper {
    public static void waitPage(WebDriver driver, long timeout) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
    }
}
