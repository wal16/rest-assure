import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.config.RedirectConfig.redirectConfig;
import static io.restassured.config.RestAssuredConfig.config;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class RestAssureBasicGet {

    public String path(String path) {
        return hostname.concat(path);
    }

    private static final String hostname =
            "https://infoshare-test-instance-one.atlassian.net";

    @Test

    public void serverInfoReturnsBaseUrl() {

        given()
                .log()
                .everything().
                when()

                .get(path("/rest/api/2/serverInfo")).
                then()
                .log()
                .everything()
                .body("baseUrl", equalTo(hostname));
    }

    @Test

    public void serverInfoReturnsHasExactVersion() {
        when()
                .get(path("/rest/api/2/serverInfo")).
                then()
                .body("versionNumbers", hasItems(1001, 0, 0));
    }

    @Test
    public void serverInfoReturnsBigDecimal()
    {
        given()
                .config(config().redirect(redirectConfig().followRedirects(true))).
        when()
                .get(path("/rest/api/2/severInfo")).
        then()
                .body("buildNumber", equalTo(100082));
    }
}
