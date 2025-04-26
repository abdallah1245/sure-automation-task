package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashBoardPage {

   private WebDriver driver;

   private WebDriverWait wait;
    public DashBoardPage(WebDriver driver) {
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    private By pimButton =By.xpath("//a[@href='/web/index.php/pim/viewPimModule']");

    private By employeeIDSearchfield =By.xpath("(//input[@class= 'oxd-input oxd-input--active'])[2]");

    private By searchButton = By.xpath("//button[@type= 'submit']");
    private By editButton = By.xpath("//button/i[@class='oxd-icon bi-pencil-fill']");

    private By contactDetailsTab = By.xpath("(//div[@class='orangehrm-tabs-wrapper'])[2]");

    public void clickOnPimButton()
    {
        driver.findElement(pimButton).click();
    }

    public void setEmployeeID(String ID) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(employeeIDSearchfield));
        driver.findElement(employeeIDSearchfield).sendKeys(ID);
    }

    public void clickOnSearchButton() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));
        driver.findElement(searchButton).click();
    }

    public void clickOnEditButton() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(editButton));
        driver.findElement(editButton).click();
    }
        public ContactDetailsPage clickOnContactDetailsTab() throws InterruptedException {
            wait.until(ExpectedConditions.visibilityOfElementLocated(contactDetailsTab));
            driver.findElement(contactDetailsTab).click();
            return new ContactDetailsPage(driver);
        }
}
