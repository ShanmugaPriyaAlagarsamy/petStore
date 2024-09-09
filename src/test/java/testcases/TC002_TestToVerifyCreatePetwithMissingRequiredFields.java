package testcases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utilities.DataProviders;
import utilities.ExtentReportUtility;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TC002_TestToVerifyCreatePetwithMissingRequiredFields extends BaseClass {

    @Test
    public void createPet_MissingRequiredFields() {
        // Create a new test in the extent report
        ExtentReportUtility.createTest("Create Pet - Missing Required Fields");

        // Define the payload with missing required fields
        String payload = "{\n" +
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
                "}";

        // Send POST request
        Response response = given()
                .header("Content-Type", "application/json")
                .body(payload)
                .post("/pet");

        // Log the response
        ExtentReportUtility.logInfo("Response: " + response.getBody().asString());

        // Check and assert the response
        if (response.getStatusCode() == 200) {
            ExtentReportUtility.logFailure("Expected error response for missing fields, but got 200 OK.");
        } else {
            response.then().statusCode(400); // Assuming 400 for missing required fields
            ExtentReportUtility.logInfo("Create Pet test with missing fields handled correctly with status code: " + response.getStatusCode());
        }
    }
}
