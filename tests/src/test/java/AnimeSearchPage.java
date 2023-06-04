import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;

public class AnimeSearchPage extends PageBase {

    private By searchBar = By.xpath("/html/body/div[2]/div[2]/div[3]/div[2]/div[3]/form/div[1]/div[1]/input");


    public AnimeSearchPage(WebDriver driver) {
        super(driver);
    }

    public SearchResultPage search(String searchQuery) {
        this.waitAndReturnElement(searchBar).sendKeys(searchQuery);
        this.waitAndReturnElement(searchBar).sendKeys(Keys.ENTER);
        return new SearchResultPage(this.driver);
    }

}