package com.qa.tests;

import com.client.RestClient;
import com.ga.base.TestBase;
import com.qa.util.TestUtil;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class GetAPITest extends TestBase {

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

    @Test(priority = 1)
    public void getAPITestWithoutHeaders() throws IOException {
        restClient=new RestClient();
        closeableHttpResponse=restClient.get(url);

        // a. Status Code
        int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status Code--->"+statusCode);

        Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_200,"Ststus is not 200");

        // b. Json String
        String responseString= EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");

        JSONObject responseJson=new JSONObject(responseString);
        System.out.println("Response JSON from API--->"+responseJson);

        //single value assertion:
        //per_page
        String perPageValue=TestUtil.getValueByJPath(responseJson,"/per_page");
        System.out.println("value of per page is---->"+perPageValue);

        Assert.assertEquals(Integer.parseInt(perPageValue),6);

        //total
        String totalValue=TestUtil.getValueByJPath(responseJson,"/total");
        System.out.println("Total value of is---->"+totalValue);

        Assert.assertEquals(Integer.parseInt(totalValue),12);

        //get the value from json Array
        String last_Name=TestUtil.getValueByJPath(responseJson,"/data[0]/last_name");
        String id=TestUtil.getValueByJPath(responseJson,"/data[0]/id");
        String avatar=TestUtil.getValueByJPath(responseJson,"/data[0]/avatar");
        String first_name=TestUtil.getValueByJPath(responseJson,"/data[0]/first_name");
        String email=TestUtil.getValueByJPath(responseJson,"/data[0]/email");

        System.out.println(last_Name);
        System.out.println(id);
        System.out.println(avatar);
        System.out.println(first_name);
        System.out.println(email);

        // c. All Headers
        Header[] headerArray=closeableHttpResponse.getAllHeaders();
        HashMap<String,String> allHeaders=new HashMap<String,String>();
        for (Header header:headerArray){
            allHeaders.put(header.getName(),header.getValue());
        }
        System.out.println("Header Array-->"+allHeaders);

    }

    @Test(priority = 2)
    public void getAPITestWithHeaders() throws IOException {
        restClient=new RestClient();

        HashMap<String,String> hashMap=new HashMap<String, String>();
        hashMap.put("Content-Type","application/json");

        closeableHttpResponse=restClient.get(url);

        // a. Status Code
        int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status Code--->"+statusCode);

        Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_200,"Ststus is not 200");

        // b. Json String
        String responseString= EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");

        JSONObject responseJson=new JSONObject(responseString);
        System.out.println("Response JSON from API--->"+responseJson);

        //single value assertion:
        //per_page
        String perPageValue=TestUtil.getValueByJPath(responseJson,"/per_page");
        System.out.println("value of per page is---->"+perPageValue);

        Assert.assertEquals(Integer.parseInt(perPageValue),6);

        //total
        String totalValue=TestUtil.getValueByJPath(responseJson,"/total");
        System.out.println("Total value of is---->"+totalValue);

        Assert.assertEquals(Integer.parseInt(totalValue),12);

        //get the value from json Array
        String last_Name=TestUtil.getValueByJPath(responseJson,"/data[0]/last_name");
        String id=TestUtil.getValueByJPath(responseJson,"/data[0]/id");
        String avatar=TestUtil.getValueByJPath(responseJson,"/data[0]/avatar");
        String first_name=TestUtil.getValueByJPath(responseJson,"/data[0]/first_name");
        String email=TestUtil.getValueByJPath(responseJson,"/data[0]/email");

        System.out.println(last_Name);
        System.out.println(id);
        System.out.println(avatar);
        System.out.println(first_name);
        System.out.println(email);

        // c. All Headers
        Header[] headerArray=closeableHttpResponse.getAllHeaders();
        HashMap<String,String> allHeaders=new HashMap<String,String>();
        for (Header header:headerArray){
            allHeaders.put(header.getName(),header.getValue());
        }
        System.out.println("Header Array-->"+allHeaders);

    }
}
