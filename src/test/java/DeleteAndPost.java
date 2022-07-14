import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpDelete;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;

public class DeleteAndPost extends BaseClass{
    @Test
    public void deleteIsSuccessful() throws IOException {
        //Para retornar 204 é necessário criar um repositório no github e um token com permissão para deletá-lo
        HttpDelete request = new HttpDelete("https://api.github.com/repos/RCarteri/testePluralsight");
        request.setHeader(HttpHeaders.AUTHORIZATION, "token " + Credentials.TOKEN);
        response = client.execute(request);

        int actualStatusCode = response.getStatusLine().getStatusCode();

        assertEquals(actualStatusCode, 204);
    }
}
