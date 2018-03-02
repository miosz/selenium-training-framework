package pageobject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    @FindBy(xpath = "//label[@for='bmi_gender_f']//span[@class='radio-btn']")
    private WebElement plecKobietaRadioButton;

    @FindBy(xpath = "//label[@for='bmi_gender_m']//span[@class='radio-btn']")
    private WebElement plecMezczyznaRadioButton;

    @FindBy(xpath = "//input[@type='number']")
    private WebElement wagaField;

    @FindBy(xpath = "//input[@type='text']")
    private WebElement wzrostField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement obliczButton;

    @FindBy(xpath = "//h5[@class='result-v1__title']")
    private WebElement bmiIndicator;

    @FindBy(xpath = "//span[@class='result-v1__title-des']//strong")
    private WebElement bmiDescription;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void calculateBMI(String sex, String weight, String height ){
        if (sex.contains("K")){
        plecKobietaRadioButton.click();}
        else {plecMezczyznaRadioButton.click();}
        wagaField.sendKeys(weight);
        wzrostField.sendKeys(height);
        obliczButton.click();
        customWait.waitForElementToBeVisible(bmiIndicator);
    }

    public WebElement getBmiIndicator() {
        return bmiIndicator;
    }

    public WebElement getBmiDescription() {
        return bmiDescription;
    }
}
