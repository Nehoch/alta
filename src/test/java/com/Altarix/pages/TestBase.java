package com.Altarix.pages;

import java.io.File;
import java.io.IOException;

import com.Altarix.helper.ApplicationManager2;
import com.Altarix.helperInterface.ApplicationManager;

import org.openqa.selenium.*;


import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;


/**
 * Base class for TestNG-based test classes
 */
public class TestBase {


    protected ApplicationManager app;
    protected WebDriver driver;


    @BeforeMethod
    public void initWebDriver() {
        try {
            if (app == null) {
                app = new ApplicationManager2();

            }
            driver = app.getWebDriver();

        } catch (Exception e) {

            throw e;
        }
    }


    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        try {
            app.stop();
        } catch (Exception e) {
            throw e;
        }
    }


}
