package com.bank.xyzbank.factories;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public enum Browsers {
    CHROME {
        public WebDriver create() {
            return new ChromeDriver();
        }
    },
    IE {
        public WebDriver create() {
            return new InternetExplorerDriver();
        }
    },
    FIREFOX {
        public WebDriver create() {
            return new FirefoxDriver();
        }
    },
    CHROME_DRIVER_MANAGER {
        public WebDriver create() {
            ChromeDriverManager.getInstance().setup();
            return new ChromeDriver();
        }
    },
    CHROME_DOCKER {
        public WebDriver create(){
            ChromeOptions options = new ChromeOptions();
            try {
                return new RemoteWebDriver(new URL("http://localhost:4444"), options);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }
        }
    };


    public WebDriver create() {
        return null;
    }
}
