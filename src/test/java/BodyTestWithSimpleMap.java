import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static entities.User.ID;
import static entities.User.LOGIN;
import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BodyTestWithSimpleMap extends BaseClass {
    CloseableHttpClient client;
    CloseableHttpResponse response;

    @BeforeMethod
    public void setUp(){
        client = HttpClientBuilder.create().build();
    }

    @AfterMethod
    public void closeResources() throws IOException {
        client.close();
        response.close();
    }

    @Test
    public void returnsCorrectLogin() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");
        response = client.execute(get);
        String jsonBody = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = new JSONObject(jsonBody);
        String loginValue = (String) getValueFor(jsonObject, LOGIN);
        assertEquals(loginValue, "andrejss88");
    }

    @Test
    public void returnsCorrectId() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");
        response = client.execute(get);
        String jsonBody = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = new JSONObject(jsonBody);
        Integer loginValue = (Integer) getValueFor(jsonObject, ID);
        assertEquals(loginValue, Integer.valueOf(11834443));
    }

    private Object getValueFor(JSONObject jsonObject, String key) {
        return jsonObject.get(key);
    }
}
