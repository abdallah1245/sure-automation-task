package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver)
    {
        this.driver=driver;

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    private By userName =By.xpath("//input[@name = 'username']");
    private By password = By.name("password");

    private By submitButton = By.cssSelector("button[type='submit']");

    public void setUserName(String name){
        driver.findElement(userName).sendKeys(name);
    }
    public void setPassword(String pass)

    {
        driver.findElement(password).sendKeys(pass);
    }

    public DashBoardPage clickOnSubmitButton()
    {
        driver.findElement(submitButton).click();
        return new DashBoardPage(driver);
    }

    public String getLoginCookie()
    {
        Cookie cookie =driver.manage().getCookieNamed("orangehrm");
        return cookie.getValue();
    }
}
