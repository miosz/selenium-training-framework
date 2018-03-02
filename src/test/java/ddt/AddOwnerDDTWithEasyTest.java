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
import pageobject.pages.NewOwner;
import utils.driver.WebDriverCreators;
import utils.driver.WebDriverProvider;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = { "src/test/resources/ownersToAdd.csv" }, loaderType = LoaderType.CSV, writeData = false)
public class AddOwnerDDTWithEasyTest {

    private static final String pageURL = "http://localhost:8080/owners/new";

    private WebDriver driver;
    private NewOwner newOwner;

    @Before
    public void setUp() {
        driver = new WebDriverProvider(WebDriverCreators.CHROME).getDriver();
        driver.manage().window().maximize();

        newOwner = new NewOwner(driver);
        driver.get(pageURL);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void addOwnerTest(
                                 @Param(name = "firstName") String firstName,
                                 @Param(name = "lastName") String lastName,
                                 @Param(name = "address") String address,
                                 @Param(name = "city") String city,
                                 @Param(name = "telephone") String telephone)
    {
        newOwner.addOwner(firstName, lastName, address, city, telephone);

        assertThat(newOwner.getH2OwnerInformation().getText()).contains("Owner Information").as("addOwnerTest failed");
    }
}
