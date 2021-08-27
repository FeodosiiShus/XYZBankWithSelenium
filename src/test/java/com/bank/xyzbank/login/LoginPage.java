package com.bank.xyzbank.login;

import com.bank.xyzbank.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Kreminskyi A.A. on авг., 2021
 */
public class LoginPage implements Page {

    @FindBy(css = "select[id='userSelect']")
    WebElement select;

    private Select selectLoginName;

    @FindBy(xpath = "//*[text() = 'Login']")
    private WebElement loginButton;

    @FindBy(css = "span[class='fontBig ng-binding']")
    private WebElement loginName;

    @FindBy(css = "button[class='btn logout']")
    private WebElement logoutButton;

    @Override
    public void init(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void chooseLoginName(String name) {
        selectLoginName = new Select(select);
        selectLoginName.selectByVisibleText(name);

    }

    public void pressButtonLogin() {
        loginButton.click();
    }

    public boolean checkLoginName(String name) {
        return loginName.getText().equals(name);
    }

    public void logout() {
        logoutButton.click();
    }
}
