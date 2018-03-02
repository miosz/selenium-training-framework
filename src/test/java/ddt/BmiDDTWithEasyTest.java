package ddt;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.loader.LoaderType;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageobject.pages.HomePage;
import pageobject.pages.LoginPage;
import pageobject.pages.RegistrationConfirmationPage;
import pageobject.pages.RegistrationPage;
import utils.driver.WebDriverCreators;
import utils.driver.WebDriverProvider;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = { "src/test/resources/bmi.xls" }, loaderType = LoaderType.EXCEL, writeData = false)
public class BmiDDTWithEasyTest {

    private static final String pageURL = "http://bmi-online.pl/";

    private WebDriver driver;
    private HomePage homePage;

    @Before
    public void setUp() {
        driver = new WebDriverProvider(WebDriverCreators.CHROME).getDriver();
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        driver.get(pageURL);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void bmiIndicatorAndDescriptionTest(
                                 @Param(name = "sex") String sex,
                                 @Param(name = "weight") String weight,
                                 @Param(name = "height") String height,
                                 @Param(name = "indicator") String indicator,
                                 @Param(name = "description") String description)
    {
        homePage.calculateBMI(sex, weight, height);

        assertThat(homePage.getBmiIndicator().getText()).contains(indicator).as("BMI indicator is wrong calculated.");
        assertThat(homePage.getBmiDescription().getText()).contains(description).as("BMI description is wrong " +
                "calculated");
    }
}
