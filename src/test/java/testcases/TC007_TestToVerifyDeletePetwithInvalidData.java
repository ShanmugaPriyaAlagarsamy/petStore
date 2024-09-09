package testcases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import io.restassured.response.Response;
import utilities.DataProviders;
import utilities.ExtentReportUtility;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TC007_TestToVerifyDeletePetwithInvalidData extends BaseClass {

    @Test
    public void deletePet_InvalidId() {
        // Create a new test in the extent report
        ExtentReportUtility.createTest("Delete Pet - Invalid ID");

        // Send DELETE request with an invalid ID
        Response response = given()
                .pathParam("petId", "999999") // Assuming this ID does not exist
                .delete("/pet/{petId}");

        // Log the response in the extent report
        ExtentReportUtility.logInfo("Response: " + response.getBody().asString());

        // Assert the response and log the result
        try {
            response.then().statusCode(404); // Assuming 404 for non-existent pet
            ExtentReportUtility.logInfo("Delete Pet test passed with invalid ID.");
        } catch (AssertionError e) {
            ExtentReportUtility.logFailure("Delete Pet test failed: " + e.getMessage());
            throw e;  // Re-throw the exception to ensure the test fails
        }
    }
}
