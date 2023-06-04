import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class MALSeleniumTest {

    private WebDriver driver;

    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
    }

    @Test
    public void testMainPageStatic() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.consent();
        Assert.assertTrue(mainPage.getWelcomeText().contains("MyAnimeList.net"));
        Assert.assertTrue(mainPage.getFooterText().contains("MyAnimeList Co.,Ltd."));
    }

    @Test
    public void testLoginPageHeadTitle() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.consent();
        LoginPage loginPage = mainPage.clickLoginPageBtn();
        Assert.assertTrue(loginPage.getHeadTitleText().contains("Login"));
    }

    @Test
    public void testLoginPageFill(){
        MainPage mainPage = new MainPage(this.driver);
        mainPage.consent();
        LoginPage loginPage = mainPage.clickLoginPageBtn();
        LoggedInAccountPage loggedInPage = loginPage.login();
        Assert.assertTrue(loggedInPage.getBodyText().contains("My"));
    }

    @Test
    public void testHoverAndSearch(){
        MainPage mainPage = new MainPage(this.driver);
        mainPage.consent();
        AnimeSearchPage dragonBallPage = mainPage.hoverAnime();
        Assert.assertTrue(dragonBallPage.getBodyText().contains("Anime Search"));
        SearchResultPage searchResult = dragonBallPage.search("Dragon Ball");
        Assert.assertTrue(searchResult.getBodyText().contains("Super"));
    }

    @Test
    public void testHistoryPreviousPage() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.consent();
        LoginPage loginPage = mainPage.clickLoginPageBtn();
        Assert.assertTrue(loginPage.getHeadTitleText().contains("Login"));
        driver.navigate().back();
        Assert.assertTrue(driver.getTitle().contains("Anime and Manga Database and Community"));
    }

    @Test
    public void testLogout(){
        MainPage mainPage = new MainPage(this.driver);
        mainPage.consent();
        LoginPage loginPage = mainPage.clickLoginPageBtn();
        LoggedInAccountPage loggedInPage = loginPage.login();
        MainPage mainPageAfterLogout = loggedInPage.logout();
        Assert.assertEquals(mainPage.getFooterText(), mainPageAfterLogout.getFooterText());
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
