import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.config.RedirectConfig.redirectConfig;
import static io.restassured.config.RestAssuredConfig.config;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;


public class RestAssureBasicGet {

    @Rule
    public WireMockRule wm = new WireMockRule(
            wireMockConfig()
                    .dynamicPort()
                    .dynamicHttpsPort()
    );

    public String path(String path) {
        return hostname.concat(path);
    }

    private static final String hostname = "https://infoshare-test-instance-one.atlassian.net";

    @Test
    public void serverInfoReturnsBaseUrl() {
        given() //request
                .log()
                .everything().
                when()
                .get(path("/rest/api/2/serverInfo")).
                then()  //response
                .log()
                .everything()
                .body("baseUrl", equalTo(hostname));
    }

    @Test
    public void serverInfoReturnsZeroAsMinorVersion() {
        when()
                .get(path("/rest/api/2/serverInfo")).
                then()
                .body("versionNumbers", hasItems(1001, 0, 0));


    }

    @Test
    public void serverInfoReturnBigDecimal()

    {
        given()
                .config(config().redirect(redirectConfig().followRedirects(true))).
                when()
                .get(path("/rest/api/2/serverInfo")).
                then()
                .body("buildNumber", equalTo(100082));
    }

}
