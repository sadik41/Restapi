package com.client;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class RestClient {

    // 1. Get Method without Header
    public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
        CloseableHttpClient httpClient=HttpClients.createDefault();
        HttpGet httpGet=new HttpGet(url); // http get request
        CloseableHttpResponse closeableHttpResponse= httpClient.execute(httpGet); //hit the GET URL

       return closeableHttpResponse;
    }

    // 2. Get Method with Headers:
    public CloseableHttpResponse get(String url,HashMap<String,String> headerMap) throws ClientProtocolException, IOException {
        CloseableHttpClient httpClient=HttpClients.createDefault();
        HttpGet httpGet=new HttpGet(url); // http get request

        for(Map.Entry<String,String> entry:headerMap.entrySet()){
            httpGet.addHeader(entry.getKey(),entry.getValue());
        }
        CloseableHttpResponse closeableHttpResponse= httpClient.execute(httpGet); //hit the GET URL

        return closeableHttpResponse;
    }
    //POST Method
    public  CloseableHttpResponse post(String url, String entityString,HashMap<String,String> headerMap) throws IOException {
        CloseableHttpClient httpClient=HttpClients.createDefault();
        HttpPost httpPost=new HttpPost(url); //http post request
        httpPost.setEntity(new StringEntity(entityString)); // for payload
        //for headers:
        for(Map.Entry<String,String> entry:headerMap.entrySet()){
            httpPost.addHeader(entry.getKey(),entry.getValue());
        }
        CloseableHttpResponse closeableHttpResponse=httpClient.execute(httpPost);
        return closeableHttpResponse;
    }

}
