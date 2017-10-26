package com.Altarix.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.Altarix.util.BaseMethod;


public class PageManager  {

    private WebDriver driver;
    private BaseMethod baseMethod;
    private DefaultPage defaultPage;
    private ServicePage servicePage;
    private SearchwsPage searchwsPage;

    public PageManager(WebDriver driver) {
        this.driver = driver;
        baseMethod = PageFactory.initElements(driver, BaseMethod.class);
        defaultPage = PageFactory.initElements(driver, DefaultPage.class);
        servicePage = PageFactory.initElements(driver, ServicePage.class);
        searchwsPage = PageFactory.initElements(driver, SearchwsPage.class);
    }


    public BaseMethod getBaseMethod() {
        if (baseMethod == null) {
            baseMethod = PageFactory.initElements(driver, BaseMethod.class);
        }
        return baseMethod;
    }



}
