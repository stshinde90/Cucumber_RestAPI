package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	static RequestSpecification req;
	ResponseSpecification responseSpec;
	public RequestSpecification requestSpecification() throws IOException
	{
		if(req==null)
		{
		PrintStream stream = new PrintStream(new FileOutputStream("logs.text"));
		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(stream))
				.addFilter(ResponseLoggingFilter.logResponseTo(stream))
				.setContentType(ContentType.JSON).build();
		//return requestSpec;
		}
		return req;
		
	}
	
	public ResponseSpecification responseSpecification()
	{
		responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return responseSpec;
	}

	public String getGlobalValue(String keyValue) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//java//resources//global.properties");
		prop.load(fis);
		return prop.getProperty(keyValue);
	}
	
	public String responseJesponceToValue(String response, String Value)
	{
		JsonPath js = new JsonPath(response);
		return js.getString(Value);
	}
}
