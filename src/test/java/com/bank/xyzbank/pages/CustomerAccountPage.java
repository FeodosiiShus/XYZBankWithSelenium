package com.bank.xyzbank.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Kreminskyi A.A. on авг., 2021
 */
public class CustomerAccountPage {

    public CustomerAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "select[id='accountSelect']")
    private WebElement selectElement;

    @FindBy(css = "div:nth-child(3) > strong:nth-child(1)")
    public WebElement accountNumberValue;

    @FindBy(xpath = "//strong[contains(text(), 'Rupee')]")
    public WebElement currencyRupee;

    @FindBy(xpath = "//strong[contains(text(), 'Dollar')]")
    public WebElement currencyDollar;

    @FindBy(xpath = "//strong[contains(text(), 'Pound')]")
    public WebElement currencyPound;

    private Select select;

    public Select chooseAccountNumber(String number) {
        select = new Select(selectElement);
        select.selectByVisibleText(number);
        return select;
    }

    public boolean checkWebElementExist(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException exception) {
            return false;
        }

    }
}
