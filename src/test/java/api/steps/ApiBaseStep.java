package api.steps;

import api.common.commonMethods;
import com.thoughtworks.gauge.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import com.thoughtworks.gauge.datastore.SpecDataStore;

import java.time.LocalDate;

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
        this.requestSpecification = given();
    }

    @Step("Send the <method> request to <endpoint>")
    public Response sendRequest(String method, String endpoint){
        switch (method) {
            case "GET":
                this.response = requestSpecification.get();
                break;
            case "POST":
                this.response = requestSpecification.post();
                break;
            default:
                Assertions.fail("Given method is not acceptable");
        }
        return this.response;
    }

    @Step("Send the <method> request to <endpoint> with <variable> as <PATH> parameter")
    public Response sendRequest(String method, String endpoint, String type, String parameter){
        String url_path = null;
        if(type.equals("UUID")){
            url_path = System.getenv(endpoint).toString().
                    replace("REPLACE_TEXT",SpecDataStore.get("uuid").toString());
        }else{
            url_path = System.getenv(endpoint).toString().
                    replace("REPLACE_TEXT",SpecDataStore.get("Country3Code").toString());
        }
        switch (method) {
            case "GET":
                if(parameter.equals("QUERY")){
                    LocalDate currentDate = LocalDate.now();
                    this.response = requestSpecification.queryParam("year",currentDate.getYear())
                            .get(Base_url + url_path);
                }else {
                this.response = requestSpecification.get(Base_url + url_path);
                }
                break;
            case "POST":
                this.response = requestSpecification.post(Base_url + url_path);
                break;
            case "DELETE":
                this.response = requestSpecification.delete(Base_url + url_path);
                break;
            default:
                Assertions.fail("Given method is not acceptable");
        }
        return this.response;
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

    @Step("Create body for CurrentYear")
    public void createBody() {
        LocalDate currentDate = LocalDate.now();
        requestSpecification.body(currentDate.getYear());
    }

    @Step("Adder Header <header> and value <value>")
    public void addHeader(String key, String Value){
        requestSpecification.header(key,Value);
    }

    @Step("Add Query Paremeter <header> and value <value>")
    public void addqueryParameter(String key, String Value){
        requestSpecification.queryParam(key,Value);
    }
}
