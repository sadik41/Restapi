package com.qa.tests;

import com.client.RestClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ga.base.TestBase;
import com.qa.data.Users;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PostAPITest extends TestBase {
    TestBase testBase;
    String servicesUrl;
    String apiUrl;
    String url;
    CloseableHttpResponse closeableHttpResponse;
    RestClient restClient;

    @BeforeMethod
    public void setUp() throws IOException {
        testBase=new TestBase();
        servicesUrl=prop.getProperty("URL");
        apiUrl=prop.getProperty("serviceURL");
        //https://reqres.in/api/users

        url=servicesUrl+apiUrl;
    }

    @Test
    public void postAPITest() throws IOException {
        restClient=new RestClient();
        HashMap<String,String> headerMap=new HashMap<String, String>();
        headerMap.put("Content-Type","application/json");

        //jackson API:
        ObjectMapper mapper=new ObjectMapper();
        Users users=new Users("morpheus","leader"); //excepted users objects

        //object to json file:
        mapper.writeValue(new File("E:\\Sadik Learnig Video JavaPython & etc\\restapi\\src\\main\\java\\com\\qa\\data\\users.json"),users);

        //object to json in String
        String usersJsonString = mapper.writeValueAsString(users);
        System.out.println(usersJsonString);

        closeableHttpResponse = restClient.post(url,usersJsonString,headerMap); //call the API

        //validate response from API
        //1. status code
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,testBase.RESPONSE_STATUS_CODE_201);

        //2. JsonString
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");

        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("The response from API is:"+ responseJson);

        //Json to java object
        Users userResObj = mapper.readValue(responseString,Users.class); //actual users object
        System.out.println(userResObj);

        Assert.assertTrue(users.getName().equals(userResObj.getName()));

        Assert.assertTrue(users.getJob().equals(userResObj.getJob()));

        System.out.println(userResObj.getId());
        System.out.println(userResObj.getCreatedAt());

    }
}
