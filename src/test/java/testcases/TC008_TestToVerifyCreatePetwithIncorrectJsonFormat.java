package testcases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import io.restassured.response.Response;
import utilities.ExtentReportUtility;

import static io.restassured.RestAssured.given;

public class TC008_TestToVerifyCreatePetwithIncorrectJsonFormat extends BaseClass {

    @Test
    public void createPet_IncorrectJSONFormat() {
        // Create a new test in the extent report
        ExtentReportUtility.createTest("Create Pet - Incorrect JSON Format");

        // Define the incorrect JSON payload
        String payload = "{\n" +
                "  \"id\": \"123\",\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"Dog\"\n" +
                "  },\n" +
                "  \"name\": \"Jimmy\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"Friendly\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "  // Missing closing brace\n";

        // Send POST request with incorrect JSON format
        Response response = given()
                .header("Content-Type", "application/json")
                .body(payload)
                .post("/pet");

        // Log the response in the extent report
        ExtentReportUtility.logInfo("Response: " + response.getBody().asString());

        // Assert the response and log the result
        try {
            response.then().statusCode(400); // Assuming 400 for incorrect JSON format
            ExtentReportUtility.logInfo("Create Pet test with incorrect JSON format passed.");
        } catch (AssertionError e) {
            ExtentReportUtility.logFailure("Create Pet test with incorrect JSON format failed: " + e.getMessage());
            throw e;  // Re-throw the exception to ensure the test fails
        }
    }
}
