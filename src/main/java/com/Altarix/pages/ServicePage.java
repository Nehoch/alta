package com.Altarix.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ServicePage extends Page {



    @FindBy(how = How.XPATH, using = "//*[@id='WSD_DataList1']//*[@face='Arial']")
    public WebElement nameService;

    @FindBy(how = How.XPATH, using = "//ul//li/a")
    @CacheLookup
    public List<WebElement> linkMethodsList;


    @FindBy(how = How.XPATH, using = "//*[@class='intro']/a")
    public WebElement returnToListMethods;

    @FindBy(how = How.XPATH, using = "//*[@class='intro'][last()]")
    public WebElement descriptionMethod;

    @FindBy(how = How.XPATH, using = "//*[@size='4']//iframe")
    public WebElement iframeMethod;

    @FindBy(how = How.XPATH, using = "//tr[2]//*[@class='frmInput']")
    public WebElement testFieldOne;

    @FindBy(how = How.XPATH, using = "//tr[3]//*[@class='frmInput']")
    public WebElement testFieldTwo;

    @FindBy(how = How.XPATH, using = "//*[@frame='box']//input[@class='button']")
    public WebElement invokeButton;


    public ServicePage(WebDriver webDriver) {
        super(webDriver);
    }
}
