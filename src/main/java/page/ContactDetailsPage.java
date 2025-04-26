package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.JsonReader;
import java.time.Duration;
import java.util.Map;


public class ContactDetailsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    String path = "src/test/resources/contactDetails.json";
    Map<String, String> contactData = JsonReader.readContactDetails(path);
    public ContactDetailsPage(WebDriver driver) {
        this.driver = driver;

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    private By street1InputField = By.xpath("//label[text()='Street 1']/parent::div/following-sibling::div//input");

    private By street2InputField = By.xpath("//label[text()='Street 2']/parent::div/following-sibling::div//input");

    private By cityInputField = By.xpath("//label[text()='City']/parent::div/following-sibling::div//input");

    private By stateInputField = By.xpath("//label[text()='State/Province']/following::input[1]");

    private By postalCode = By.xpath("//label[text()='Zip/Postal Code']/following::input[1]");

    private By countryDropDownMenu = By.xpath("(//label[text()='Country']/following::div[contains(@class,'oxd-select-text')])[1]");

    private By homePhone = By.xpath("//label[text()='Home']/following::input[1]");
    private By mobile = By.xpath("//label[text()='Mobile']/following::input[1]");
    private By workPhone = By.xpath("//label[text()='Work']/following::input[1]");

    private By workEmail = By.xpath("//label[text()='Work Email']/following::input[1]");

    private By anotherEmail = By.xpath("//label[text()='Other Email']/following::input[1]");

    private By saveButton = By.xpath("//button[@type='submit']");

    private By successMessage = By.className("oxd-toast-content-text");

    private By addAttachmentButton = By.xpath("(//button[@type='button'])[4]");

    private By addFile = By.xpath("//input[@type='file']");

    private By saveAttachmentButton = By.xpath("(//button[@type='submit'])[2]");

    private By attachmentRecord = By.xpath("//div[@class='oxd-table-card']");

    private By jobTab = By.xpath("//a[contains(@class,'orangehrm-tabs-item') and text()='Job']");

    public void fillContactForm() throws InterruptedException {
        fillStreet1(contactData.get("street1"));
        fillStreet2(contactData.get("street2"));
        fillCity(contactData.get("city"));
        fillState(contactData.get("state"));
        fillPostalCode(contactData.get("zip"));
        fillCountry(contactData.get("country"));
        fillHomePhone(contactData.get("homePhone"));
        fillMobile(contactData.get("mobile"));
        fillWorkPhone(contactData.get("workPhone"));
        fillWorkEmail(contactData.get("workEmail"));
        fillOtherEmail(contactData.get("anotherEmail"));
    }

// 👇 Each field interaction is separated below

    public void fillStreet1(String value) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(street1InputField));
        driver.findElement(street1InputField).sendKeys(value);

    }

    public void fillStreet2(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(street2InputField));
        driver.findElement(street2InputField).sendKeys(value);
    }

    public void fillCity(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cityInputField));
        driver.findElement(cityInputField).sendKeys(value);
    }

    public void fillState(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(stateInputField))
                .sendKeys(value);
    }

    public void fillPostalCode(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(postalCode))
                .sendKeys(value);
    }
    public void fillCountry(String value)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(countryDropDownMenu));
        driver.findElement(countryDropDownMenu).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + value + "']")));
        driver.findElement(By.xpath("//span[text()='" + value + "']")).click();

    }
    public void fillHomePhone(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(homePhone))
                .sendKeys(value);
    }

    public void fillMobile(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(mobile))
                .sendKeys(value);
    }

    public void fillWorkPhone(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(workPhone))
                .sendKeys(value);
    }

    public void fillWorkEmail(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(workEmail))
                .sendKeys(value);
    }

    public void fillOtherEmail(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(anotherEmail))
                .sendKeys(value);
    }

    public void saveContactForm() {
        driver.findElement(saveButton).click();
    }
    public void uploadAttachment(String filePath) {
        driver.findElement(addAttachmentButton).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(addFile));
        driver.findElement(addFile).sendKeys(filePath);
        driver.findElement(saveAttachmentButton).click();
    }

    public WebElement getRecord()
    {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(attachmentRecord));
    }

    public JobDetailsPage clickOnJobTab()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(jobTab))
                .click();
        return new JobDetailsPage(driver);
    }

    public String getSuccessMessage()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return driver.findElement(successMessage).getText();
    }

}
