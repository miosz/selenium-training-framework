package pageobject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.waits.CustomWait;

public class BasePage {
    protected WebDriver driver;
    protected CustomWait customWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        customWait = new CustomWait(driver);
    }
}