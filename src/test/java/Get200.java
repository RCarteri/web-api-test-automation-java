import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class Get200 extends BaseClass {
    @DataProvider
    private Object[][] endpoints(){
        return new Object[][]{
                {""},
                {"/rate_limit"},
                {"/search/repositories?q=java"}
        };
    }

    @Test(dataProvider = "endpoints")
    public void baseUrlReturns200(String endpoint) throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT);
        response = client.execute(get);
        int status = response.getStatusLine().getStatusCode();
        assertEquals(status, 200);
    }
}
