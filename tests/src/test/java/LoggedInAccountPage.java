import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoggedInAccountPage extends PageBase{

    private By userBtn = By.xpath("/html/body/div[2]/div[2]/div[1]/div[2]/div[8]/a");
    private By logoutBtn = By.xpath("/html/body/div[2]/div[2]/div[1]/div[2]/div[8]/div/ul/li[11]/form/a");

    public LoggedInAccountPage(WebDriver driver) {
        super(driver);
    }

    public MainPage logout() {
        this.waitAndReturnElement(userBtn).click();
        this.waitAndReturnElement(logoutBtn).click();
        return new MainPage(this.driver);
    }
}
