package testcases;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utilities.DataProviders;
import utilities.ExtentReportUtility;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TC001_TestToVerifyCreatePetwithValidData extends BaseClass {

    @Test(dataProvider = "petstoreData", dataProviderClass = DataProviders.class)
    public void createPet_ValidData(String id, String name, String status, String category, String tags) {
        // Create a new test in the extent report
        ExtentReportUtility.createTest("Create Pet - Valid Data with ID: " + id);

        // Define the payload
        String payload = "{\n" +
                "  \"id\": \"" + id + "\",\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"" + category + "\"\n" +
                "  },\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"" + tags + "\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"" + status + "\"\n" +
                "}";

        // Send POST request to create a pet
        Response response = given()
                .header("Content-Type", "application/json")
                .body(payload)
                .post("/pet");

        // Log the response in the extent report
        ExtentReportUtility.logInfo("Response: " + response.getBody().asString());

        // Assert the response and log failure if necessary
        try {
            response.then().statusCode(200)
                .body("id", equalTo(Integer.parseInt(id)))
                .body("name", equalTo(name))
                .body("status", equalTo(status));
            ExtentReportUtility.logInfo("Create Pet test passed.");
        } catch (AssertionError e) {
            ExtentReportUtility.logFailure("Create Pet test failed: " + e.getMessage());
            throw e;  // Re-throw the exception to ensure the test fails
        }
    }
}
