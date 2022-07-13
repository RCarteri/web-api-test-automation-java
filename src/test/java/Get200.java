import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class Get200 {
    public static final String BASE_ENDPOINT = "http://api.github.com";
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
    public void baseUrlReturn200() throws IOException {
        HttpGet get = new HttpGet("https://api.github.com");
        response = client.execute(get);
        int status = response.getStatusLine().getStatusCode();
        assertEquals(status, 200);
    }

    @Test
    public void rateLimitReturns200() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/rate_limit");
        response = client.execute(get);
        int status = response.getStatusLine().getStatusCode();
        assertEquals(status, 200);
    }

    @Test
    public void searchReposReturns200() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/search/repositories?q=java");
        response = client.execute(get);
        int status = response.getStatusLine().getStatusCode();
        assertEquals(status, 200);
    }
}
