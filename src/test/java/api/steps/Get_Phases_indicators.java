package api.steps;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import org.json.JSONArray;
import static org.junit.jupiter.api.Assertions.*;

public class Get_Phases_indicators extends ApiBaseStep {

    @Step("and response should include <count> Phases")
    public void ValidatePhases(int count) {
        Gauge.writeMessage(response.prettyPrint());
        JSONArray jsonarray = new JSONArray(response.getBody().asString());
        System.out.println(jsonarray.length());
        assertEquals(count,jsonarray.length());
    }
}
