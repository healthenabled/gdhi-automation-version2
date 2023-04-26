package api.steps;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetStaticData_Steps extends ApiBaseStep {

    @Step("and response should include <count> Phases")
    public void ValidatePhases(int count) {
        Gauge.writeMessage(response.prettyPrint());
        JSONArray jsonarray = new JSONArray(response.getBody().asString());
        System.out.println(jsonarray.length());
        assertEquals(count,jsonarray.length());
    }

    @Step("and response should include <cat_count> categories and <indi_count> indicators")
    public void ValidateIndicators(int categories, int indicators) {
        JSONArray jsonarray = new JSONArray(response.getBody().asString());
        Gauge.writeMessage("No of Categories =>" + jsonarray.length());
        int expected_indicators =0;
        for(int i=0;i<jsonarray.length();i++){
            expected_indicators = expected_indicators  +
                     jsonarray.getJSONObject(i).getJSONArray("indicators").length();
        }
        Gauge.writeMessage("No of Health Indicators =>" + expected_indicators);
        assertEquals(categories,jsonarray.length());
        assertEquals(indicators,expected_indicators);
        Gauge.writeMessage(response.prettyPrint());
    }

    @Step("and response should include <193> countries")
    public void ValidateCountries(int count) {
        JSONArray jsonarray = new JSONArray(response.getBody().asString());
        Gauge.writeMessage("No of Countries =>" + jsonarray.length());
        System.out.println(jsonarray.length());
        assertEquals(count,jsonarray.length());
    }

    @Step("and response should include below <count> categories and Overall Score")
    public void ValidateGlobalIndicators(int count){
        JSONObject jsonobj = new JSONObject(response.getBody().asString());
        System.out.println(jsonobj.getInt("overAllScore"));
        assertTrue(((jsonobj.getInt("overAllScore") >0) && jsonobj.getInt("overAllScore")<8));
        JSONArray jsonarray = new JSONArray(jsonobj.getJSONArray("categories"));
        assertEquals(jsonarray.length(),count);
    }

    @Step("and response should include below regions <table>")
    public void validateregions(Table table){
        JSONArray jsonarray = new JSONArray(response.getBody().asString());
        Gauge.writeMessage("No of Regions =>" + response.prettyPrint());
        ArrayList<String> actualIds = new ArrayList<>();
        ArrayList<String> RegionNames = new ArrayList<>();
        for (Object arr : jsonarray) {
            JSONObject jsonObj = (JSONObject) arr;
            actualIds.add(jsonObj.getString("regionId"));
            RegionNames.add(jsonObj.getString("regionName"));
        }
        assertEquals(table.getColumnValues("id"),actualIds);
        assertEquals(table.getColumnValues("RegionName"),RegionNames);
    }
}
