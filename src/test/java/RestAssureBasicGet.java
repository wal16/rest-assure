import org.junit.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

public class RestAssureBasicGet {

    public String path(String path) {
        return hostname.concat(path);
    }

    private static final String hostname =
            "https://infoshare-test-instance-one.atlassian.net";

    @Test

    public void serverInfoReturnsBaseUrl() {
        when()

                .get(path("/rest/api/2/serverInfo")).
                then()
                .body("baseUrl", equalTo(hostname));
    }
}
