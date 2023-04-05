package api.steps;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.SpecDataStore;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.*;

public class Admin_Steps extends ApiBaseStep{

    @Step("Validate the response should be Country success should be <true>")
    public void ValidateGenerateResponse(boolean value) {
        Gauge.writeMessage(response.prettyPrint());
        JSONObject jsonobj = new JSONObject(response.getBody().asString());
        assertTrue(jsonobj.getBoolean("success"));
        assertEquals("null",jsonobj.get("existingStatus").toString());
    }

    @Step("Verify the country is not Available in Country Status Summary")
    public void ValidateRejected(){
        GetUrl("COUNTRIES_SUMMARY_STATUS", "GDHI_Endpoint");
        Response response = sendRequest("GET","COUNTRIES_SUMMARY_STATUS");
        JSONObject jsonobj = new JSONObject(response.getBody().asString());
        int country_count =0;
        for (String key : jsonobj.keySet()) {
            if(key.equals("REVIEW_PENDING")){
                JSONArray jsonarray = new JSONArray(jsonobj.getJSONArray(key));
                for (Object obj : jsonarray) {
                    JSONObject jsonObj = (JSONObject) obj;
                    if(jsonObj.getString("countryName").toLowerCase().
                            equals(SpecDataStore.get("CountryName").toString().toLowerCase())){
                        country_count++;
                    }
                }
                assertEquals(0,country_count);

            }
        }
    }


}
