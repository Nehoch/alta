package com.Altarix.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Sample page
 */
public class DefaultPage extends Page {

  @FindBy(how = How.XPATH, using = "//*[@id='TOPWS_TOPWSX']//a")
  @CacheLookup
  public List<WebElement> topWebServicesList;

  @FindBy(how = How.XPATH, using = "//*[@id='Top1_WSNew']//a")
  @CacheLookup
  public List<WebElement> newWebServicesList;

  @FindBy(how = How.XPATH, using = "//*[@name='key']")

  public WebElement seachField;

  @FindBy(how = How.XPATH, using = "//*[@value='Search']")

  public WebElement seachButton;

  public DefaultPage(WebDriver webDriver) {
    super(webDriver);
  }
}
