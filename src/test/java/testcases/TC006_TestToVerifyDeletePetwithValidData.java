package testcases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import io.restassured.response.Response;
import utilities.DataProviders;
import utilities.ExtentReportUtility;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TC006_TestToVerifyDeletePetwithValidData extends BaseClass {

    @Test(dataProvider = "petIdProvider", dataProviderClass = DataProviders.class)
    public void deletePet_ValidId(String id) {
        // Create a new test in the extent report
        ExtentReportUtility.createTest("Delete Pet - Valid ID with ID: " + id);

        // Send DELETE request to delete the pet
        Response response = given()
                .pathParam("petId", id)
                .delete("/pet/{petId}");

        // Log the response in the extent report
        ExtentReportUtility.logInfo("Response: " + response.getBody().asString());

        // Assert the response and log the result
        try {
            response.then().statusCode(200)
                .body("message", equalTo(id)); // Assuming the message contains the deleted pet ID
            ExtentReportUtility.logInfo("Delete Pet test passed with valid ID.");
        } catch (AssertionError e) {
            ExtentReportUtility.logFailure("Delete Pet test failed: " + e.getMessage());
            throw e;  // Re-throw the exception to ensure the test fails
        }
    }
}
