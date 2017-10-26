package com.Altarix.helper;

import org.openqa.selenium.WebDriver;
import com.Altarix.helperInterface.ApplicationManager;
import com.Altarix.util.BaseMethod;
import com.Altarix.util.Browser;
import com.Altarix.util.PropertyLoader;
import com.Altarix.webdriver.WebDriverFactory;

public class ApplicationManager2 implements ApplicationManager {


    private WebDriver driver;
    private String baseUrl;
    private NavigationHelper2 navigateHelper;

    public ApplicationManager2() {
        baseUrl = PropertyLoader.loadProperty("site.url");
        String gridHubUrl = PropertyLoader.loadProperty("grid2.hub");
        Browser browser = new Browser();
        browser.setName(PropertyLoader.loadProperty("browser.name"));
        browser.setVersion(PropertyLoader.loadProperty("browser.version"));
        browser.setPlatform(PropertyLoader.loadProperty("browser.platform"));

        String username = PropertyLoader.loadProperty("user.username");
        String password = PropertyLoader.loadProperty("user.password");
        driver = (WebDriverFactory.getInstance(gridHubUrl, browser,
                username, password));
        driver.manage().window().maximize();
    }

    @Override
    public void stop() {
        driver.close();

        try {
            Thread.sleep(3000);
            driver.quit();
        } catch (Exception e) {
        }
    }


    public WebDriver getWebDriver() {
        return driver;
    }

    public NavigationHelper2 getNavigateHelper() {
        if (navigateHelper == null) {
            navigateHelper = new NavigationHelper2(this);
        }
        return navigateHelper;
    }


    public String getBaseUrl() {
        return baseUrl;
    }

}
