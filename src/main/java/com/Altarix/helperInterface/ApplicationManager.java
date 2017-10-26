package com.Altarix.helperInterface;

import org.openqa.selenium.WebDriver;
import com.Altarix.helper.*;
import com.Altarix.util.BaseMethod;

public interface ApplicationManager {

    void stop();

    WebDriver getWebDriver();

    String getBaseUrl();

    NavigationHelper2 getNavigateHelper();

}
