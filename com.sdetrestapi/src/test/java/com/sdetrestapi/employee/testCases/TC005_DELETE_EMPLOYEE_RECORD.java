package com.sdetrestapi.employee.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sdetrestapi.employee.Base.TestBase;
import com.sdetrestapi.employee.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_DELETE_EMPLOYEE_RECORD extends TestBase
{

	RequestSpecification httpRequest;
	Response response;

	String empName=RestUtils.empName();
	String empSalary=RestUtils.empSal();
	String empAge=RestUtils.empAge();


	@BeforeClass
	public void deleteEmployee() throws InterruptedException
	{
		logger.info("****************Started TC005_DELETE_EMPLOYEE_RECORD******************");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();

	
         response=httpRequest.request(Method.GET,"employees");
		
		// First get the JsonPath object instance from the Response interface
         
         JsonPath jsonPathEvaluator=response.jsonPath();
         
         //Capture id
         
         String empID= jsonPathEvaluator.get("[0].id");
         response =httpRequest.request(Method.DELETE,"/delete/"+empID);//Pass ID to delete record

		Thread.sleep(3);


	}

	@Test

	public void checkResponseBody() 
	{
		String responseBody=response.getBody().asString();
		Assert.assertEquals(responseBody.contains("successfully! deleted Records"),true);
		

	}

	@Test
	public void checkStatusCode()
	{
		int statusCode=response.getStatusCode();
		Assert.assertEquals(statusCode, 200);


	}


	@Test
	public void checkStatusLine()
	{
		String statusLine=response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");


	}

	@Test

	public void checkContentType() 
	{


		String ContentType=response.header("Content-Type");
		Assert.assertEquals(ContentType,"application/json;charset=utf-8");

	}

	@Test
	public void checkServerType() 
	{


		String serverType=response.header("Server");
		Assert.assertEquals(serverType, "nginx/1.16.0");

	}

	
	/*
	 * @Test
	 * 
	 * public void checkcontentEncoding() {
	 * 
	 * 
	 * String contentEncoding=response.header("Content-Encoding");
	 * Assert.assertEquals(contentEncoding, "gzip");
	 * 
	 * }
	 */
	
	@AfterClass
	
	public void tearDown() 
	{
		
		logger.info("***********************Finished TC003_POST_EMPLOYEE_RECORD*************************");
		
	}

}
