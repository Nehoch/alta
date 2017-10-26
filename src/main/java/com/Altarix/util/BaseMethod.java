package com.Altarix.util;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.ScreenshotException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.Altarix.pages.PageManager;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BaseMethod {
    protected WebDriver driver;

    protected PageManager pages;
    private static final String SCREENSHOT_FOLDER = "target/screenshots/";
    private static final String SCREENSHOT_FORMAT = ".png";
    public BaseMethod(WebDriver driver) {
        this.driver = driver;
    }

    public void openLinkInNewTab(WebElement element) {
        String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
        element.sendKeys(selectLinkOpeninNewTab);
    }

    public void input(WebElement element, String sendText, Boolean swich) {
        if (swich) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView();", element);
        }
        while (true) {
            boolean breakIt = true;
            try {
                element.clear();
                if (!sendText.equals(""))
                    element.sendKeys(sendText);
            } catch (Exception e) {
                if (e.getMessage().contains("element is not attached")) {
                    breakIt = false;
                }
            }
            if (breakIt) {
                break;
            }
        }
    }

    public void click(WebElement element, Boolean useJQuery, Boolean swich) {
        waitForAjax();
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        if (useJQuery) {
            js.executeScript("arguments[0].click();", element);
        } else {
            if (swich) {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView();", element);
            }
            retryingFindClick(element);
        }
        waitForAjax();
    }

    public void retryingFindClick(WebElement element) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                element.click();
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
    }

    public boolean waitForAjax() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean jQcondition = false;

        try {
            if (!driver.getCurrentUrl().contains("e.mail.ru")) {
                new WebDriverWait(driver, 180) {
                }.until(new ExpectedCondition<Boolean>() {

                    @Override
                    public Boolean apply(WebDriver driverObject) {
                        return (Boolean) ((JavascriptExecutor) driverObject)
                                .executeScript("return jQuery.active == 0");
                    }
                });
                jQcondition = (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return window.jQuery != undefined && jQuery.active === 0") && (Boolean) ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                return jQcondition;
            } else
                return true;
        } catch (Exception e) {
            return jQcondition;
        }

    }

    public void takeScreenshot() {
        try {
            WebDriver returned = new Augmenter().augment(driver);
            if (returned != null) {
                File f = ((TakesScreenshot) returned).getScreenshotAs(OutputType.FILE);
                try {
                    FileUtils.copyFile(f,
                            new File(SCREENSHOT_FOLDER + generateData() + SCREENSHOT_FORMAT));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (ScreenshotException se) {
            se.printStackTrace();
        }
    }


    public String generateData() {
        StringBuilder builder = new StringBuilder();

        String dateStr = String.valueOf(new Date());
        DateFormat readFormat = new SimpleDateFormat(
                "EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

        DateFormat writeFormat = new SimpleDateFormat("MM_dd_HH_mm_ss");
        Date date = null;
        try {
            date = readFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String ch = "";
        if (date != null) {
            ch = writeFormat.format(date);
        }
        builder.append(ch);
        return ch;
    }


    public Boolean isElementPresent(WebElement element) {
        waitForAjax();
        Boolean isPresent;
        try {
            waitForAjax();
            if (element.isEnabled()) {

            }
            isPresent = true;
        } catch (Exception e) {
            isPresent = false;
        }
        return isPresent;
    }

    public void openAndSwitchToNewTab(WebElement element) {
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        openLinkInNewTab(element);
        ArrayList<String> newTabFull = new ArrayList<String>(driver.getWindowHandles());
        newTabFull.removeAll(newTab);
        waitForAjax();
        driver.switchTo().window(newTabFull.get(0));


    }
}
