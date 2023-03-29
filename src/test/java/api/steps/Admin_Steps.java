package api.steps;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import org.json.JSONArray;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Admin_Steps extends ApiBaseStep{

    @Step("Validate the response should be Country success should be <true>")
    public void ValidateGenerateResponse(boolean value) {
        Gauge.writeMessage(response.prettyPrint());
        JSONObject jsonobj = new JSONObject(response.getBody().asString());
        assertTrue(jsonobj.getBoolean("success"));
        assertEquals("null",jsonobj.get("existingStatus").toString());
    }


}
