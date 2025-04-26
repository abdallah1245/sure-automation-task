package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import page.DashBoardPage;
import page.LoginPage;

import java.io.File;
import java.io.IOException;

public class BaseTest {
    private WebDriver driver;

    protected LoginPage loginPage;
    protected static String cookie;
    protected static String employeeId;
    protected static String empNumber;
    protected DashBoardPage dashBoardPage;
    protected ExtentSparkReporter spark;
    protected ExtentReports extent;
    protected static ExtentTest test ;


    @BeforeSuite
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        spark = new ExtentSparkReporter("Automation Test Report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }
    @BeforeClass
    public void loginAndCaptureCookie() {
        test = extent.createTest("Login and Capture Cookie");
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new LoginPage(driver);
        loginPage.setUserName("Admin");
        loginPage.setPassword("admin123");
        dashBoardPage = loginPage.clickOnSubmitButton();
        cookie = loginPage.getLoginCookie();

        Assert.assertNotNull(cookie, "Session cookie must not be null");
        test.pass("Cookie captured: " + cookie);

        RestAssured.config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", 15000)
                        .setParam("http.socket.timeout", 15000)
                        .setParam("http.connection-manager.timeout", 15000L));
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = captureScreenshot(result.getMethod().getMethodName());
            extent.createTest(result.getMethod().getMethodName()).fail(result.getThrowable()).addScreenCaptureFromPath(screenshotPath);
        }
    }
    public String captureScreenshot(String methodName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String dest = System.getProperty("user.dir") + "/Screenshots/" + methodName + ".png";
            File destination = new File(dest);
            FileUtils.copyFile(source, destination);
            return dest;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
    @AfterSuite
    public void tearDown() {
        extent.flush();
    }
}
