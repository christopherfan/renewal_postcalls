
import java.io.*;

import org.apache.http.client.ClientProtocolException;

import org.apache.http.client.fluent.Request;

import org.apache.http.impl.client.BasicResponseHandler;



import org.apache.http.entity.ContentType;
import org.apache.http.HttpResponse;
import org.json.simple.JSONObject;



public class Main {

    public static void main(String[] args) {

        JSONObject post_body = new JSONObject();
        post_body.put("ActivationID","d369-0eb7-1326-4f31-aacf-b403-ae2f-04dc");
        post_body.put("Type","Subscription");
        post_body.put("RemainingDays", 25);
        post_body.put("Product", "PhotoPrint");
        post_body.put("SoldTo", "Candy Store");
        post_body.put("TotalQuantity", 100);
        post_body.put("ExpirationDate", "Dec 11, 2017");
        post_body.put("Entitlement", "9dc4-88f9-b8bf-48c0-a567-e578-617c-edaa");
        String url = "https://calm-earth-14228.herokuapp.com/todo/api/v1.0/items";


        System.out.println("Starting POST Rest Calls to App\n ");

        HttpResponse post_response;
        try {

            System.out.println("\n Posting to Heroku");
            post_response = postRequestWithBody(url, post_body.toJSONString());
            String responseString = new BasicResponseHandler().handleResponse(post_response);
            System.out.println("\n Post Response");
            System.out.println(responseString);

        }catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("\n Verify POST successful in GET Statement");

        try {


            String rest_response = Request.Get("https://calm-earth-14228.herokuapp.com/todo/api/v1.0/items")
                    .execute().returnContent().asString();

            System.out.println("\nDoes this work>>>>>>>>>>");
            System.out.println(rest_response);

        }catch (IOException ioe ){
            System.out.println(ioe);
        }

    }

    public static HttpResponse postRequestWithBody(String url, String json) throws ClientProtocolException, IOException {
        return Request.Post(url)
                .addHeader("app-header", "example")
                .bodyString(json, ContentType.APPLICATION_JSON)
                .execute().returnResponse();
    }


}
