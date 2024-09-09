package testcases;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import utilities.DataProviders;
import utilities.ExtentReportUtility;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TC003_TestToVerifyGetPetwithValidData extends BaseClass {

    @Test(dataProvider = "petIdProvider", dataProviderClass = DataProviders.class)
    public void getPet_ValidId(String id) {
        // Create a new test in the extent report
        ExtentReportUtility.createTest("Get Pet - Valid ID with ID: " + id);
        
        // Send GET request to retrieve a pet by ID
        Response response = given()
                .pathParam("petId", id)
                .get("/pet/{petId}");
        
        // Log the response in the extent report
        ExtentReportUtility.logInfo("Response: " + response.getBody().asString());
        
        // Assert the response and log failure if necessary
        try {
            response.then().statusCode(200)
                .body("id", equalTo(Integer.parseInt(id)));
            ExtentReportUtility.logInfo("Get Pet test with valid ID passed.");
        } catch (AssertionError e) {
            ExtentReportUtility.logFailure("Get Pet test with valid ID failed: " + e.getMessage());
            throw e;  // Re-throw the exception to ensure the test fails
        }
    }
}
