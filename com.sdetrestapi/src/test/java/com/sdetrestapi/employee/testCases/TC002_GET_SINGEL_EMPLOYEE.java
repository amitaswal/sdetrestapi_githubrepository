package com.sdetrestapi.employee.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sdetrestapi.employee.Base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_GET_SINGEL_EMPLOYEE  extends TestBase
{

	RequestSpecification httpRequest;
	Response response;

	@BeforeClass

	public void getSingleEmployee() throws InterruptedException
	{

		logger.info("********************* Started TC002_GET_Single_EMPLOYEEE*************************"); 
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/employee"+empID);
		Thread.sleep(3);

	}

	@Test

	public void checkResponseBody() 
	{
		
		String responseBody=response.getBody().asString();
		Assert.assertTrue(responseBody!=null);

	}

	@Test
	public void checkStatusCode() 
	{

		 
		int statusCode=response.getStatusCode();
		Assert.assertEquals(statusCode,200);

	}


	@Test

	public void checkResponseTime() 
	{

		
		long responseTime=response.getTime();
		Assert.assertTrue(responseTime<10000);

	}		
	@Test

	public void checkstatusLine() 
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

	@Test

	public void checkcontentEncoding() 
	{

		
		String contentEncoding=response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding, "gzip");

	}


	@Test

	public void checkcontentLenght() 
	{
		String contentlength=response.header("Content-Length");
		//Assert.assertTrue(Integer.parseInt(contentLength)>100);
		int ContentLength=Integer.parseInt(contentlength);
		Assert.assertEquals(ContentLength,135);
	
	}


	
	@AfterClass
	
	public void tearDown()
	{
		
		logger.info("*********************Finished TC002_GET_SINGEL_EMPLOYEE*************************");
		
	}
}
