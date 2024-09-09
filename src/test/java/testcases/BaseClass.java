package testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.RestAssured;
import utilities.ExtentReportUtility;

public class BaseClass {
	
	
	public static ExtentReports extent;
	public static ExtentTest test;
	
	/*
	 * @BeforeSuite public void setupExtentReports() {
	 * ExtentReportUtility.setupExtentReport(); }
	 * 
	 * @AfterSuite public void teardownExtentReports() {
	 * ExtentReportUtility.flushReports(); }
	 */
	    @BeforeClass
	    public void setup() {
	       	        
	        // Set the base URI globally for all test cases
	        RestAssured.baseURI = "https://petstore.swagger.io/v2";
	    }
	    
	   
	    }
	    
	    


