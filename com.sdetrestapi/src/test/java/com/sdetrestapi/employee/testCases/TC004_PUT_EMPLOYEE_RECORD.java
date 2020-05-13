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
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_PUT_EMPLOYEE_RECORD extends TestBase
{

	RequestSpecification httpRequest;
	Response response;

	String empName=RestUtils.empName();
	String empSalary=RestUtils.empSal();
	String empAge=RestUtils.empAge();


	@BeforeClass
	public void updateEmployee() throws InterruptedException
	{
		logger.info("****************Started TC004_PUT_EMPLOYEE_RECORD******************");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();

		JSONObject requestParams=new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("salary", empSalary);
		requestParams.put("age", empAge);

		//Add a header stating the Request body is a JSON
		httpRequest.header("Content-Type","application/json");


		//Add the JSON to the  body of the JSON
		httpRequest.body(requestParams.toJSONString());

		response=httpRequest.request(Method.PUT, "/update/"+empID);

		Thread.sleep(5000);


	}

	@Test

	public void checkResponseBody() 
	{
		String responseBody=response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empName),true);
		Assert.assertEquals(responseBody.contains(empSalary),true);
		Assert.assertEquals(responseBody.contains(empAge),true);

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
