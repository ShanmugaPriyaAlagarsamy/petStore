package testcases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import io.restassured.response.Response;
import utilities.ExtentReportUtility;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TC004_TestToVerifyGetPetwithInValidData extends BaseClass {

    @Test
    public void getPet_InvalidId() {
        // Create a new test in the extent report
        ExtentReportUtility.createTest("Get Pet - Invalid ID");

        // Define an invalid pet ID
        String invalidId = "999999"; // Assuming this ID does not exist

        // Send GET request to fetch pet by invalid ID
        Response response = given()
                .pathParam("petId", invalidId)
                .get("/pet/{petId}");

        // Log the response in the extent report
        ExtentReportUtility.logInfo("Response: " + response.getBody().asString());

        // Check and assert the response
        try {
            response.then().statusCode(404); // Assuming 404 for non-existent pet
            ExtentReportUtility.logInfo("Get Pet test with invalid ID returned the expected 404 status code.");
        } catch (AssertionError e) {
            ExtentReportUtility.logFailure("Get Pet test with invalid ID failed: " + e.getMessage());
            throw e;  // Re-throw the exception to ensure the test fails
        }
    }
}
