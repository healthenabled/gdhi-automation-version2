package api.steps;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class yearsSteps extends ApiBaseStep {

    @Step("and response should include <Version1> as default version")
    public void ValidateBFF(String Version) {
        Gauge.writeMessage(response.prettyPrint());
        JSONObject jsonobj = new JSONObject(response.getBody().asString());
        assertEquals(Version,jsonobj.getString("defaultYear"));
    }

}
