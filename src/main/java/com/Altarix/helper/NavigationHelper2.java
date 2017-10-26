package com.Altarix.helper;

import com.Altarix.helperInterface.NavigationHelper;
import org.openqa.selenium.WebElement;


public class NavigationHelper2 extends DriverBasedHelper implements
        NavigationHelper {

    public NavigationHelper2(ApplicationManager2 manager) {
        super(manager.getWebDriver());
        this.baseUrl = manager.getBaseUrl();
    }

    public void navigateGotoUrl(String text) {
        pages.getBaseMethod().waitForAjax();
        driver.navigate().to(text);
        pages.getBaseMethod().waitForAjax();
    }

    public void switchToIframe(WebElement element) {
        driver.switchTo().frame(element);
        pages.getBaseMethod().waitForAjax();
    }

    private String baseUrl;

    public void openMainPage() {
        pages.getBaseMethod().waitForAjax();
        driver.get(baseUrl);
        pages.getBaseMethod().waitForAjax();
    }

    public String getMeUrl() {
        return baseUrl;
    }

}
