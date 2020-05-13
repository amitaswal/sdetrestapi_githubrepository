package com.sdetrestapi.employee.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sdetrestapi.employee.Base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_GET_All_EMPLOYEEE extends TestBase
{
	@BeforeClass

	public void getAllEmployee() throws InterruptedException
	{

		logger.info("********************* Started TC001_GET_All_EMPLOYEEE*************************"); 
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET,"/employees");
		Thread.sleep(3);

	}


	@Test

	public void checkResponseBody() 
	{
		logger.info("*********************Checking Respone Body*************************"); 
		String responseBody=response.getBody().asString();
		logger.info("Response Body==>"+responseBody);
		Assert.assertTrue(responseBody!=null);

	}

	@Test

	public void checkStatusCode() 
	{

		logger.info("*********************Checking Respone Code*************************"); 
		int statusCode=response.getStatusCode();//Getting status code
		logger.info("Status Code is==>"+statusCode);//200
		Assert.assertEquals(statusCode, 200);

	}


	@Test

	public void checkResponseTime() 
	{

		logger.info("*********************Checking Respone Time*************************"); 
		long responseTime=response.getTime();
		logger.info("Response Time is==>"+responseTime);

		if(responseTime>2000) 
		
			logger.warn("Response Time is greater than 2000");
          Assert.assertTrue(responseTime<10000);

	}		
	@Test

	public void checkstatusLine() 
	{

		logger.info("*********************Checking Status Line*************************"); 
		String statusLine=response.getStatusLine();
		logger.info("Status Line is ==>"+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}


	@Test

	public void checkContentType() 
	{

		logger.info("*********************Checking ContentType*************************"); 
		String ContentType=response.header("Content-Type");
		logger.info("Content Type is ==>"+ContentType);
		Assert.assertEquals(ContentType, "application/json;charset=utf-8");

	}

	@Test

	public void checkServerType() 
	{

		logger.info("*********************Checking Server Type*************************"); 
		String serverType=response.header("Server");
		logger.info("Server Type is ==>"+serverType);
		Assert.assertEquals(serverType, "nginx/1.16.0");

	}

	@Test

	public void checkcontentEncoding() 
	{

		logger.info("*********************Checking Content Encoding*************************"); 
		String contentEncoding=response.header("Content-Encoding");
		logger.info("Content-Encoding==>"+contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");

	}


	@Test

	public void checkcontentLenght() 
	{

		logger.info("*********************Checking Content Lenghth*************************"); 
		String contentLength=response.header("Content-Lenghth");
		logger.info("Content-Lenghth==>"+contentLength);
	
		
		/*
		 * if (Integer.parseInt(contentLength)>100)
		 * logger.info("Content Length is less than 100");
		 * Assert.assertTrue(Integer.parseInt(contentLength)>100);
		 */

	}


	@Test

	public void checkCookies() 
	{

		logger.info("*********************Checking Cookies*************************"); 
		String cookies=response.getCookie("PHPSESSID");

	}
	
	@AfterClass
	
	public void tearDown()
	{
		
		logger.info("*********************Finished TC001_GET_All_EMPLOYEEE*************************");
		
	}



}
