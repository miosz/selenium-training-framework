package pageobject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewOwner extends BasePage{

    @FindBy(xpath = "//input[@id='firstName']")
    private WebElement firsNameField;

    @FindBy(xpath = "//input[@id='lastName']")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@id='address']")
    private WebElement addressField;

    @FindBy(xpath = "//input[@id='city']")
    private WebElement cityField;

    @FindBy(xpath = "//input[@id='telephone']")
    private WebElement telephoneField;

    @FindBy(xpath = "//button[@class='btn btn-default']")
    private WebElement addOwnerButton;

    @FindBy(xpath = "//h2[contains(text(),'Owner Information')]")
    private WebElement h2OwnerInformation;

    public NewOwner(WebDriver driver) {
        super(driver);
    }

    public WebElement getH2OwnerInformation() {
        return h2OwnerInformation;
    }

    public void addOwner(String firstName, String lastName, String address, String city, String telephone){
        firsNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        addressField.sendKeys(address);
        cityField.sendKeys(city);
        telephoneField.sendKeys(telephone);
        addOwnerButton.click();
        customWait.waitForElementToBeVisible(h2OwnerInformation);
    }
}
