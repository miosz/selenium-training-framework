package ddt;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pageobject.pages.NewOwner;
import utils.driver.WebDriverCreators;
import utils.driver.WebDriverProvider;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(DataProviderRunner.class)
public class AddOwnerDDTWithTngTech {

    private static final String PAGE_URL = "http://localhost:8080/owners/new";

    private WebDriver driver;
    private NewOwner newOwner;

    @DataProvider
    public static Object[][] addOwnerTest() {
        return new String[][] {
                new String[] {"A1", "B1", "C1", "D1", "11"},
                new String[] {"A2", "B2", "C2", "D2", "12"},
                new String[] {"A3", "B3", "C3", "D3", "13"},
                new String[] {"A4", "B4", "C4", "D4", "14"},
                new String[] {"A5", "B5", "C5", "D5", "15"},
                new String[] {"A6", "B6", "C6", "D6", "16"},
                new String[] {"A7", "B7", "C7", "D7", "17"},
                new String[] {"A8", "B8", "C8", "D8", "18"},
        };
    }

    @Before
    public void setUp() {
        driver = new WebDriverProvider(WebDriverCreators.CHROME).getDriver();
        driver.manage().window().maximize();

        newOwner = new NewOwner(driver);

        driver.get(PAGE_URL);
    }

    @Test
    @UseDataProvider("addOwnerTest")
    public void addOwnerTest(String firstName, String lastName, String address, String city, String telephone) {
        newOwner.addOwner(firstName, lastName, address, city, telephone);

        assertThat(newOwner.getH2OwnerInformation().getText()).contains("Owner Information").as("addOwnerTest failed");
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
