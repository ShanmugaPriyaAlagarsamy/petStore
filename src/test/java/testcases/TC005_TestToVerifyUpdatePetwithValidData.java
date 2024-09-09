package testcases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import io.restassured.response.Response;
import utilities.DataProviders;
import utilities.ExtentReportUtility;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TC005_TestToVerifyUpdatePetwithValidData extends BaseClass {

    @Test(dataProvider = "updatePetProvider", dataProviderClass = DataProviders.class)
    public void updatePet_ValidData(String id, String updatedName, String updatedStatus, String category, String tags) {
        // Create a new test in the extent report
        ExtentReportUtility.createTest("Update Pet - Valid Data with ID: " + id);

        // Define the payload for updating the pet
        String updatedPayload = "{\n" +
                "  \"id\": \"" + id + "\",\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"" + category + "\"\n" +
                "  },\n" +
                "  \"name\": \"" + updatedName + "\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"" + tags + "\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"" + updatedStatus + "\"\n" +
                "}";

        // Send PUT request to update the pet
        Response response = given()
                .header("Content-Type", "application/json")
                .body(updatedPayload)
                .put("/pet");

        // Log the response in the extent report
        ExtentReportUtility.logInfo("Response: " + response.getBody().asString());

        // Assert the response and log the result
        try {
            response.then().statusCode(200)
                .body("id", equalTo(Integer.parseInt(id)))
                .body("name", equalTo(updatedName))
                .body("status", equalTo(updatedStatus));
            ExtentReportUtility.logInfo("Update Pet test passed with valid data.");
        } catch (AssertionError e) {
            ExtentReportUtility.logFailure("Update Pet test failed: " + e.getMessage());
            throw e;  // Re-throw the exception to ensure the test fails
        }
    }
}
