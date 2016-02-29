package imdb.Steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class CommonStep {

    private WebDriver webDriver;

    public String top250PageUrl = "http://www.imdb.com/chart/top";
    public String firstResultOnTop250ListSelector = ".//*[@id='main']/div/span/div/div/div[2]/table/tbody/tr[1]";
    public String firstResultOnGenreListSelector = ".//*[@id='main']/table/tbody/tr[2]";
    public String sortingOptionSelector = ".lister-sort-by";
    public String revertSortingSelector = ".global-sprite.lister-sort-reverse";

    @Before
    public void setUp() {
        String separator = System.getProperty("file.separator");

        String chromePath = System.getProperty("user.dir")
                + separator + "src"
                + separator + "test"
                + separator + "resources"
                + separator +"chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", chromePath);

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
        webDriver = new ChromeDriver(capabilities);

        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.manage().window().setSize(new Dimension(1600,900));
        webDriver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        webDriver.manage().deleteAllCookies();
        webDriver.close();
    }

    @When("user goes to top250 imdb page")
    public void userGoesToTop250Page() {
        webDriver.get(top250PageUrl);
    }

    @Then("there is at least one result on page")
    public void checkResultsOnPage() {
        if (webDriver.getCurrentUrl().startsWith(top250PageUrl)) {
            webDriver.findElement(By.xpath(firstResultOnTop250ListSelector));
        } else {
            webDriver.findElement(By.xpath(firstResultOnGenreListSelector));
        }

    }

    @When("user sorts by \"([^\"]*)\"")
    public void userSortsTop250Page(String sortingOption) {
        Select sortOption = new Select(webDriver.findElement(By.cssSelector(sortingOptionSelector)));
        sortOption.selectByVisibleText(sortingOption);
    }

    @When("user reverts order of sorting")
    public void userRevertsOrderOnTop250Page() {
        webDriver.findElement(By.cssSelector(revertSortingSelector)).click();
    }

    @When("clicks on \"([^\"]*)\" genre")
    public void userClicksOnGenreOption(String genreName) {
        webDriver.findElement(
                By.xpath(".//*[@id='sidebar']/div[7]/span/ul/li[contains(.,'" + genreName + "')]/a")
        ).click();
    }

}