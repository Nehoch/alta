package com.Altarix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

public class Tests extends TestBase {

    private DefaultPage defaultPage;
    private ServicePage servicePage;
    private SearchwsPage searchwsPage;
    private static String oldTab;

    @BeforeMethod
    public void initPageObjects() {
        defaultPage = PageFactory.initElements(driver, DefaultPage.class);
        searchwsPage = PageFactory.initElements(driver, SearchwsPage.class);
        servicePage = PageFactory.initElements(driver, ServicePage.class);
        oldTab = driver.getWindowHandle();

    }

    @AfterMethod
    public void qiut() throws Exception {
        try {
            ArrayList<String> newTab = new ArrayList<String>(driver
                    .getWindowHandles());
            for (String tab : newTab) {
                if (!tab.equals(oldTab)) {
                    driver.switchTo().window(tab);
                    driver.close();
                }
            }
            driver.switchTo().window(oldTab);
            driver.manage().deleteAllCookies();
        } catch (Exception e) {
            throw e;
        }
    }


    @Test
    public void testNewTabAndSeach() throws Exception {
        app.getNavigateHelper().openMainPage();

        app.getNavigateHelper().pages.getBaseMethod().openLinkInNewTab(defaultPage.topWebServicesList.get(0));
        app.getNavigateHelper().pages.getBaseMethod().openLinkInNewTab(defaultPage.topWebServicesList.get(1));
        app.getNavigateHelper().pages.getBaseMethod().openLinkInNewTab(defaultPage.newWebServicesList.get(defaultPage.newWebServicesList.size() - 2));
        app.getNavigateHelper().pages.getBaseMethod().openLinkInNewTab(defaultPage.newWebServicesList.get(defaultPage.newWebServicesList.size() - 1));
        app.getNavigateHelper().pages.getBaseMethod().input(defaultPage.seachField, "Global Weather", true);
        app.getNavigateHelper().pages.getBaseMethod().click(defaultPage.seachButton, false, true);
        assertThat(
                searchwsPage.resultSeachList.get(0).getText(),
                equalToIgnoringCase("Global Weather"));
        app.getNavigateHelper().pages.getBaseMethod().openAndSwitchToNewTab(searchwsPage.resultSeachList.get(0));
        app.getNavigateHelper().pages.getBaseMethod().waitForAjax();
        Assert.assertTrue(driver.getPageSource().contains("Click here</a> to open new window"), "Page service not contains text \"Click here to open new window\"");
        driver.switchTo().window(oldTab);
        app.getNavigateHelper().openMainPage();
        app.getNavigateHelper().pages.getBaseMethod().input(defaultPage.seachField, "GlobalWeather", true);
        app.getNavigateHelper().pages.getBaseMethod().click(defaultPage.seachButton, false, true);
        Assert.assertTrue(searchwsPage.resultSeachList.size() == 0, "Contains rezult seach for request \"GlobalWeather\"");

    }


    @Test
    public void testService() throws Exception {
        app.getNavigateHelper().openMainPage();
        String titleService = defaultPage.newWebServicesList.get(2).getText();

        app.getNavigateHelper().pages.getBaseMethod().openAndSwitchToNewTab(defaultPage.newWebServicesList.get(2));
        app.getNavigateHelper().pages.getBaseMethod().waitForAjax();


        assertThat(
                servicePage.nameService.getText(),
                equalToIgnoringCase(titleService));
        app.getNavigateHelper().switchToIframe(servicePage.iframeMethod);


        for (int i = 0; i < servicePage.linkMethodsList.size(); i++) {
            WebElement element = driver.findElement(By.xpath("//ul//li[" + (i + 1) + "]/a"));

            app.getNavigateHelper().pages.getBaseMethod().click(element, false, true);
            Assert.assertTrue(app.getNavigateHelper().pages.getBaseMethod().isElementPresent(servicePage.returnToListMethods), "Not found link for returned to list methods");
            Assert.assertTrue(app.getNavigateHelper().pages.getBaseMethod().isElementPresent(servicePage.descriptionMethod), "Not found description method");

            app.getNavigateHelper().pages.getBaseMethod().takeScreenshot();
            app.getNavigateHelper().pages.getBaseMethod().click(servicePage.returnToListMethods, false, true);

        }
    }

    // Тестирование сервиса GlobalWeather
    @Test
    public void testServiceGlobalWeather() throws Exception {
        app.getNavigateHelper().openMainPage();
        app.getNavigateHelper().pages.getBaseMethod().input(defaultPage.seachField, "Global Weather", true);
        app.getNavigateHelper().pages.getBaseMethod().click(defaultPage.seachButton, false, true);
        assertThat(
                searchwsPage.resultSeachList.get(0).getText(),
                equalToIgnoringCase("Global Weather"));
        app.getNavigateHelper().pages.getBaseMethod().click(searchwsPage.resultSeachList.get(0), false, true);
        assertThat(
                servicePage.nameService.getText(),
                equalToIgnoringCase("Global Weather"));
        app.getNavigateHelper().switchToIframe(servicePage.iframeMethod);


        WebElement element = driver.findElement(By.xpath("//ul//li[1]/a"));
        app.getNavigateHelper().pages.getBaseMethod().click(element, false, true);
        Assert.assertTrue(app.getNavigateHelper().pages.getBaseMethod().isElementPresent(servicePage.returnToListMethods), "Not found link for returned to list methods");
        Assert.assertTrue(app.getNavigateHelper().pages.getBaseMethod().isElementPresent(servicePage.descriptionMethod), "Not found description method");
        app.getNavigateHelper().pages.getBaseMethod().input(servicePage.testFieldOne, "rus", true);
        app.getNavigateHelper().pages.getBaseMethod().openAndSwitchToNewTab(servicePage.invokeButton);

        driver.switchTo().window(oldTab);
        assertThat(
                servicePage.nameService.getText(),
                equalToIgnoringCase("Global Weather"));
        app.getNavigateHelper().switchToIframe(servicePage.iframeMethod);

        app.getNavigateHelper().pages.getBaseMethod().input(servicePage.testFieldOne, "123", true);
        app.getNavigateHelper().pages.getBaseMethod().openAndSwitchToNewTab(servicePage.invokeButton);
        driver.switchTo().window(oldTab);
        assertThat(
                servicePage.nameService.getText(),
                equalToIgnoringCase("Global Weather"));
        app.getNavigateHelper().switchToIframe(servicePage.iframeMethod);
        app.getNavigateHelper().pages.getBaseMethod().click(servicePage.returnToListMethods, false, true);

        element = driver.findElement(By.xpath("//ul//li[2]/a"));
        app.getNavigateHelper().pages.getBaseMethod().click(element, false, true);
        Assert.assertTrue(app.getNavigateHelper().pages.getBaseMethod().isElementPresent(servicePage.returnToListMethods), "Not found link for returned to list methods");
        Assert.assertTrue(app.getNavigateHelper().pages.getBaseMethod().isElementPresent(servicePage.descriptionMethod), "Not found description method");
        app.getNavigateHelper().pages.getBaseMethod().input(servicePage.testFieldOne, "rus", true);
        app.getNavigateHelper().pages.getBaseMethod().input(servicePage.testFieldTwo, "rus", true);
        app.getNavigateHelper().pages.getBaseMethod().openAndSwitchToNewTab(servicePage.invokeButton);

        driver.switchTo().window(oldTab);
        assertThat(
                servicePage.nameService.getText(),
                equalToIgnoringCase("Global Weather"));
    }
}
