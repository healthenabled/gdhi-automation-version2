package api.steps;

import com.thoughtworks.gauge.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class ApiBaseStep {

    public String Base_url;
    public static Response response;
    public static RequestSpecification requestSpecification;

    @Step("Given the Phases exists in the <GDHI_BaseUrl>")
    public void GetUrl(String baseurl) {
        Base_url = System.getenv("API_URL");
    }

    @Step("Send the <GET> request to <PHASES>")
    public Response sendRequest(String method, String path){
        Base_url = System.getenv("API_URL");
        System.out.println(Base_url +"------"+ System.getenv(path));
        RestAssured.baseURI = Base_url + System.getenv(path);
        requestSpecification = given();
        switch (method) {
            case "GET":
                response = requestSpecification.get();
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
}
