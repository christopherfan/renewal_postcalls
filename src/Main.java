import java.net.*;
import java.io.*;

import java.util.Map;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.entity.ContentType;
import org.apache.http.HttpResponse;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;



public class Main {

    private String apiusername;
    private String apipassword;
    private String apiURL;

    public static void main(String[] args) {

        JSONObject post_body = new JSONObject();
        post_body.put("ActivationID","d369-0eb7-1326-4f31-aacf-b403-ae2f-04dc");
        post_body.put("Type","Subscription");
        post_body.put("RemainingDays", 25);
        post_body.put("Product", "PhotoPrint");
        post_body.put("SoldTo", "Warren Paper");
        post_body.put("TotalQuantity", 50);
        post_body.put("ExpirationDate", "May 1, 2017");
        post_body.put("Entitlement", "9dc4-88f9-b8bf-48c0-a567-e578-617c-edaa");



        System.out.println("Hello World!");
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.google.com");
        HttpResponse post_response;
        ResponseHandler<String> responseHandler=new BasicResponseHandler();


        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);

            String rest_response = Request.Get("https://calm-earth-14228.herokuapp.com/todo/api/v1.0/items")
                    .execute().returnContent().asString();

            System.out.println("\nDoes this work>>>>>>>>>>");
            System.out.println(rest_response);

        }catch (IOException ioe ){
            System.out.println(ioe);
        }

        try {

            System.out.println("\n Posting to Heroku");
            post_response = postRequestWithBody(post_body.toJSONString());
            String responseString = new BasicResponseHandler().handleResponse(post_response);
            System.out.println("\n Post Response");
            System.out.println(responseString);

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static HttpResponse postRequestWithBody(String json) throws ClientProtocolException, IOException {
        return Request.Post("https://calm-earth-14228.herokuapp.com/todo/api/v1.0/items")
                .addHeader("app-header", "example")
                .bodyString(json, ContentType.APPLICATION_JSON)
                .execute().returnResponse();
    }


}
