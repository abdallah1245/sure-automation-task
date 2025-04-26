package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JobDetailsPage {
  private WebDriver driver;

   private WebDriverWait wait ;

    LocalDate today = LocalDate.now();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    String todayFormatted = today.format(formatter);
    String oneYearLaterFormatted = today.plusYears(1).format(formatter);
    public JobDetailsPage(WebDriver driver)
    {
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    private By joinedDate = By.xpath("//label[text()='Joined Date']/following::input[1]");

    private By jobTitleDropDown = By.xpath("(//label[text()='Job Title']/following::div[contains(@class,'oxd-select-text')])[1]");

    private By softwareEngineerOption = By.xpath("//span[text()='Software Engineer']");

    private By employmentStatusOption =By.xpath("(//label[text()='Employment Status']/following::div[contains(@class,'oxd-select-text')])[1]");

    private By partTimeInterShipOption = By.xpath("//span[text()='Part-Time Internship']");

    private By subUnitDropDown = By.xpath("(//label[text()='Sub Unit']/following::div[contains(@class,'oxd-select-text')])[1]");

    private By qualityAssuranceOption = By.xpath("//span[text()='Quality Assurance']");

    private By locationDropDown = By.xpath("(//label[text()='Location']/following::div[contains(@class,'oxd-select-text')])[1]");

    private By locationOption = By.xpath("//span[text()='Texas R&D']");

    private By checkBox = By.xpath("//div[@class = 'oxd-switch-wrapper']");

    private By contractStartDate = By.xpath("//label[text()='Contract Start Date']/following::input[1]");

    private By contractEndDate = By.xpath("//label[text()='Contract End Date']/following::input[1]");

    private By saveButton = By.xpath("//button[@type='submit']");

    private By successMessage = By.className("oxd-toast-content-text");

    public void SelectJoinDate(String date)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(joinedDate));
        driver.findElement(joinedDate).sendKeys(date);
    }

    public void selectJobTitle()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(jobTitleDropDown));
        driver.findElement(jobTitleDropDown).click();
       wait.until(ExpectedConditions.visibilityOfElementLocated(softwareEngineerOption));
       driver.findElement(softwareEngineerOption).click();

    }

    public void selectEmploymentStatus()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(employmentStatusOption));
        driver.findElement(employmentStatusOption).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(partTimeInterShipOption));
        driver.findElement(partTimeInterShipOption).click();

    }
    public void selectSubUnit()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(subUnitDropDown));
        driver.findElement(subUnitDropDown).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(qualityAssuranceOption));
        driver.findElement(qualityAssuranceOption).click();

    }
    public void selectLocation()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locationDropDown));
        driver.findElement(locationDropDown).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(locationOption));
        driver.findElement(locationOption).click();

    }
    public void clickOnCheckBox()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkBox));
        driver.findElement(checkBox).click();
    }
    public void selectContractStartDate()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(contractStartDate));
        driver.findElement(contractStartDate).sendKeys(todayFormatted);

    }
    public void selectContractEndDate()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(contractEndDate));
        driver.findElement(contractEndDate).sendKeys(oneYearLaterFormatted);

    }

    public void clickOnSaveButton()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(saveButton));
        driver.findElement(saveButton).click();
    }
    public String getSuccessMessage()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
       return driver.findElement(successMessage).getText();
    }

}
