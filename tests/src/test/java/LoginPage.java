import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageBase {

    private final By emailInput = By.xpath("/html/body/div[2]/div[2]/div[3]/div[2]/table/tbody/tr/td/form/div/p[1]/input");
    private final By passwordInput = By.xpath("/html/body/div[2]/div[2]/div[3]/div[2]/table/tbody/tr/td/form/div/p[2]/input");
    private final By loginBtn = By.xpath("/html/body/div[2]/div[2]/div[3]/div[2]/table/tbody/tr/td/form/div/p[6]/input");
    private String email = "ineqo9";
    private String password = "Topsecretpwd123";
    private By headTitle = By.xpath("/html/body/div[2]/div[2]/div[3]/div[2]/table/tbody/tr/td/div/p");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public String getHeadTitleText() {
        return this.waitAndReturnElement(headTitle).getText();
    }

    public LoggedInAccountPage login() {
        this.waitAndReturnElement(emailInput).sendKeys(email);
        this.waitAndReturnElement(passwordInput).sendKeys(password);
        this.waitAndReturnElement(loginBtn).click();
        return new LoggedInAccountPage(this.driver);
    }

}
