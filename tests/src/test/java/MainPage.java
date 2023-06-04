import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;



public class MainPage extends PageBase {

    private By loginPageBtn = By.xpath("//*/a[@id='malLogin']");
    private By footer = By.xpath("/html/body/footer");
    private By consentText = By.xpath("/html/body/div[1]/div/div/div/div[1]/div/div/figure/h2");
    private By consentBtn = By.xpath("/html/body/div[1]/div/div/div/div[2]/div/button[3]");
    private By headTitleBy = By.xpath("/html/body/div[2]/div[2]/div[4]/div[1]/h1");
    private By animeBtn = By.xpath("/html/body/div[2]/div[2]/div[3]/div[2]/ul/li[1]/a");
    private By animeSearchOption = By.xpath("/html/body/div[2]/div[2]/div[3]/div[2]/ul/li[1]/ul/li[1]/a");

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://myanimelist.net/");
    }

    public String getFooterText() {
        return this.waitAndReturnElement(footer).getText();
    }

    public String getWelcomeText() {
        return this.waitAndReturnElement(headTitleBy).getText();
    }

    public void consent(){
        this.waitAndReturnElement(consentBtn).click();
    }

    public LoginPage clickLoginPageBtn() {
        this.waitAndReturnElement(loginPageBtn).click();
        return new LoginPage(this.driver);
    }

    public AnimeSearchPage hoverAnime(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Actions actions = new Actions(driver);
        WebElement animeDropdown = driver.findElement(animeBtn);
        actions.moveToElement(animeDropdown).build().perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        WebElement animeSearch = driver.findElement(animeSearchOption);
        actions.click(animeSearch).perform();
        return new AnimeSearchPage(driver);
    }
}
