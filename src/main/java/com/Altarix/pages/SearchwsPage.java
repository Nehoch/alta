package com.Altarix.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Sample page
 */
public class SearchwsPage extends Page {



  @FindBy(how = How.XPATH, using = "//*[@id='WSD_WSL']//a")
  public List<WebElement> resultSeachList;

  public SearchwsPage(WebDriver webDriver) {
    super(webDriver);
  }
}
