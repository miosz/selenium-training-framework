package ddt;


import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
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

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

@RunWith(DataProviderRunner.class)
public class BmiDDTWithTngTech {

    private static final String PAGE_URL = "http://bmi-online.pl/";

    private WebDriver driver;

    private HomePage homePage;

    /* Instead of String[] we can use Object[] or other type. */
    @DataProvider
    public static Object[][] bmiIndicatorAndDescriptionTest() {
        return new String[][] {
                new String[] {"M", "40", "178", "12.62", "wygłodzenie"},
                new String[] {"K", "52", "178", "16.41", "wychudzenie"},
                new String[] {"M", "55", "178", "17.36", "niedowagę"},
                new String[] {"K", "60", "178", "18.94", "wagę prawidłową"},
                new String[] {"M", "80", "178", "25.25", "nadwagę"},
                new String[] {"K", "96", "178", "30.3", "I stopień otyłości"},
                new String[] {"M", "120", "178", "37.87", "II stopień otyłości"},
                new String[] {"K", "130", "178", "41.03", "III stopień otyłości"},
        };
    }

    @Before
    public void setUp() {
        driver = new WebDriverProvider(WebDriverCreators.CHROME).getDriver();
        driver.manage().window().maximize();

        homePage = new HomePage(driver);

        driver.get(PAGE_URL);
    }

    @Test
    @UseDataProvider("bmiIndicatorAndDescriptionTest")
    public void bmiIndicatorAndDescriptionTest(String sex, String weight, String height, String indicator, String description) {
        homePage.calculateBMI(sex, weight, height);

        assertThat(homePage.getBmiIndicator().getText()).contains(indicator).as("BMI indicator is wrong calculated.");
        assertThat(homePage.getBmiDescription().getText()).contains(description).as("BMI description is wrong " +
                "calculated");

    }

    @After
    public void tearDown() {
        driver.close();
    }
}
