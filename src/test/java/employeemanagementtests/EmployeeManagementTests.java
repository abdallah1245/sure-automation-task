package employeemanagementtests;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.ContactDetailsPage;
import page.JobDetailsPage;
import testutils.ApiUtils;
import testutils.Payloads;

import static testutils.TestUtils.generateRandomNumericId;

public class EmployeeManagementTests extends BaseTest {
    private ContactDetailsPage contactDetailsPage;
    private JobDetailsPage jobDetailsPage;
    @Test(priority = 1)
    public void addEmployeeViaRestAssured() {
        test = extent.createTest("Add Employee");

        employeeId = generateRandomNumericId(5);

        Response response = RestAssured.given().log().all()
                .spec(ApiUtils.getRequestSpec(cookie))
                .basePath("pim/employees")
                .body(Payloads.getAddEmployeePayload(employeeId))
                .when().post();

        empNumber = response.jsonPath().getString("data.empNumber");
        Assert.assertNotNull(empNumber);
        test.pass("Employee created: " + empNumber);
    }

    @Test(priority = 2)
    public void updatePersonalDetailsViaRestAssured() {
        test = extent.createTest("Update Personal Details");

        RestAssured.given().log().all()
                .spec(ApiUtils.getRequestSpec(cookie))
                .basePath("pim/employees/" + empNumber + "/personal-details")
                .body(Payloads.getUpdatePersonalDetailsPayload(employeeId))
                .when().put()
                .then().log().all();

        test.pass("Personal details updated for empNumber: " + empNumber);
    }

    @Test(priority = 3)
    public void fillContactDetailsUI() throws InterruptedException {
        test = extent.createTest("Fill Contact Details in UI");

        dashBoardPage.clickOnPimButton();
        dashBoardPage.setEmployeeID(employeeId);
        dashBoardPage.clickOnSearchButton();
        dashBoardPage.clickOnEditButton();
        contactDetailsPage = dashBoardPage.clickOnContactDetailsTab();
        contactDetailsPage.fillContactForm();
        contactDetailsPage.uploadAttachment(System.getProperty("user.dir") +"\\src\\test\\resources\\demo.txt");
        Assert.assertTrue(contactDetailsPage.getRecord().isDisplayed());
        contactDetailsPage.saveContactForm();
        Assert.assertTrue(contactDetailsPage.getSuccessMessage().contains("Success"));
    }

    @Test(priority = 4)
    public void fillJobDetailsUI() {
        test = extent.createTest("Fill Job Details in UI");

        jobDetailsPage = contactDetailsPage.clickOnJobTab();

        jobDetailsPage.SelectJoinDate("2015-06-15");
        jobDetailsPage.selectJobTitle();
        jobDetailsPage.selectEmploymentStatus();
        jobDetailsPage.selectSubUnit();
        jobDetailsPage.selectLocation();
        jobDetailsPage.clickOnCheckBox();
        jobDetailsPage.selectContractStartDate();
        jobDetailsPage.selectContractEndDate();
        jobDetailsPage.clickOnSaveButton();

        Assert.assertTrue(jobDetailsPage.getSuccessMessage().contains("Success"));
    }

}
