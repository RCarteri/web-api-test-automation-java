import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.Arrays;
import java.util.List;

public class ResponseUtils {
    public static String getHeader(CloseableHttpResponse response, String headerName) {
        //Get all Headers
        Header[] headers = response.getAllHeaders();
        List<Header> httpHeaders = Arrays.asList(headers);
        String returnHeader = "";

        //Loop over the headers list
        for (Header header : httpHeaders) {
            if (headerName.equalsIgnoreCase(header.getName())) {
                returnHeader = header.getValue();
            }
        }

        //If no header found throw an exception
        if (returnHeader.isEmpty()){
            throw new RuntimeException("Didn't find any header: " + headerName);
        }

        //Return the header
        return returnHeader;
    }

    public static String getHeaderJava8Way(CloseableHttpResponse response, String headerName) {
        //Get all Headers
        List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());
        Header matchHeader = httpHeaders.stream()
                .filter(header -> headerName.equalsIgnoreCase(header.getName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Didn't find any header: " + headerName));
        return matchHeader.getValue();
    }

    public static boolean headerIsPresent(CloseableHttpResponse response, String headerName) {
        //Get all Headers
        List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());
        return httpHeaders.stream()
                .anyMatch(header -> header.getName().equalsIgnoreCase(headerName));
    }
}
