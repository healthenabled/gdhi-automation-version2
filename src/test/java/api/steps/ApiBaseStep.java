package api.steps;

import api.common.commonMethods;
import com.thoughtworks.gauge.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class ApiBaseStep {

    public String Base_url;
    public static Response response;
    public static RequestSpecification requestSpecification;

    @Step("Given the <endpoint> exists in the <GDHI_BaseUrl>")
    public void GetUrl(String name, String baseurl) {
        Base_url = System.getenv("API_URL");
        RestAssured.baseURI = Base_url + System.getenv(name);
        System.out.println(RestAssured.baseURI);
        requestSpecification = given();
    }

    @Step("Send the <method> request to <endpoint>")
    public Response sendRequest(String method, String path){
        switch (method) {
            case "GET":
                response = requestSpecification.get();
                break;
            case "POST":
                response = requestSpecification.post();
                break;
            default:
                Assertions.fail("Given method is not acceptable");
        }
        return response;
    }

    @Step("Then the Status code should be <code>")
    public void sendRequest(String code){
        int statusCode = response.getStatusCode();
        assertTrue(statusCode == Integer.valueOf(code),
                "Status code is not " + code + ". Actual status code is " + statusCode);
    }

    @Step("Create body for <parameter>")
    public void createBody(String key) {
        commonMethods cm = new commonMethods();
        String body = cm.getbody(key);
        requestSpecification.body(body);
    }
}
