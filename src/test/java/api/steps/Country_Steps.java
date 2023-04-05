package api.steps;

import api.common.commonMethods;
import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.DataStore;
import com.thoughtworks.gauge.datastore.SpecDataStore;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Country_Steps extends ApiBaseStep{

    commonMethods cm = new commonMethods();

    @Step("Get the UUID, Country Codes for the Country <INDIA>")
    public void GetCountryDetails(String CountryName) {
        GetUrl("COUNTRIES", "GDHI_Endpoint");
        Response response = sendRequest("GET","COUNTRIES");
        JSONArray jsonarray = new JSONArray(response.getBody().asString());
        try{
            for (Object obj : jsonarray) {
                JSONObject jsonObj = (JSONObject) obj;
                if(CountryName.toLowerCase().equals(jsonObj.getString("name").toLowerCase())){
                    SpecDataStore.put("CountryName", CountryName);
                    SpecDataStore.put("Country3Code", jsonObj.getString("id"));
                    SpecDataStore.put("Country2Code", jsonObj.getString("alpha2Code"));
                    SpecDataStore.put("uuid", jsonObj.getString("uniqueId"));
                    break;
                }
            }
//            jsonarray.forEach(item -> {
//                JSONObject obj = (JSONObject) item;
//                if(CountryName.toLowerCase().equals(obj.getString("name").toLowerCase())){
//                    SpecDataStore.put("CountryName", CountryName);
//                    SpecDataStore.put("Country3Code", obj.getString("id"));
//                    SpecDataStore.put("Country2Code", obj.getString("alpha2Code"));
//                    SpecDataStore.put("uuid", obj.getString("uniqueId"));
//                    return;
//                }
//            });
        }catch(Exception e){
          e.printStackTrace();
        }
    }

    @Step("Verify the Country Status Summary with Status <New>")
    public void VerifyCountrySummaryStatus(String status){
        GetUrl("COUNTRIES_SUMMARY_STATUS", "GDHI_Endpoint");
        Response response = sendRequest("GET","COUNTRIES_SUMMARY_STATUS");
        Gauge.writeMessage(response.prettyPrint());
        JSONObject jsonobj = new JSONObject(response.getBody().asString());
        for (String key : jsonobj.keySet()) {
            if(key.toLowerCase().equals(status.toLowerCase())){
                JSONArray jsonarray = new JSONArray(jsonobj.getJSONArray(key));
                for (Object obj : jsonarray) {
                    JSONObject jsonObj = (JSONObject) obj;
                    if(SpecDataStore.get("CountryName").toString().toLowerCase().equals(jsonObj.getString("countryName").toLowerCase())){
                        assertEquals(status.toLowerCase(),jsonObj.getString("status").toLowerCase());
                        break;
                    }
                }
                break;
            }
        }
    }

    @Step("Verify the Country questionnaire data")
    public void VerifyCountryData(){
        GetUrl("COUNTRY_DATA", "GDHI_Endpoint");
        Response response = sendRequest("GET","COUNTRY_DATA","UUID","path");
    }

    @Step("Verify the Country Summary is Displayed as expected")
    public void VerifyTheSummaryDetails(){
        Gauge.writeMessage(response.prettyPrint());
        JSONObject actual = new JSONObject(response.getBody().asString());
        String body = cm.getbody("country_data");
        JSONObject expected = new JSONObject(body).getJSONObject("countrySummary");
        assertEquals(actual.length(), expected.length());
    }

    @Step("Verify the Development indicators data is Displayed as expected")
    public void VerifydevelopmentData() {
        Gauge.writeMessage(response.prettyPrint());
        JSONObject actual = new JSONObject(response.getBody().asString());
        assertEquals(actual.getString("countryId"),SpecDataStore.get("Country3Code"));
    }

    @Step("Verify the Country  data is Displayed overall indicators data")
    public void VerifyCountryDataOverall(){
        JSONObject jsonobj = new JSONObject(response.getBody().asString());
        JSONArray actual = jsonobj.getJSONArray("countryHealthScores");
        for (Object obj : actual) {
            JSONObject jsonObj = (JSONObject) obj;
            if (SpecDataStore.get("CountryName").toString().toLowerCase().equals(jsonObj.getString("countryName").toLowerCase())) {
                assertEquals(3, jsonObj.getInt("countryPhase"));
                String MonthYear = cm.getCurrentMonth()+" "+cm.getCurrentyear();
                assertEquals(MonthYear,jsonObj.getString("updatedDate"));
                break;
            }
        }

    }

    @Step("Verify the Benchmark data is Displayed as expected")
    public void VerifyBenkmarkData(){
        JSONObject jsonobj = new JSONObject(response.getBody().asString());
        String actual = jsonobj.getJSONObject("1").getString("benchmarkValue");
        List<String> list = List.of("At", "Below", "Above");
        assertTrue(list.contains(actual));
    }

    @Step("Validate the Published Attributes in the response")
    public void VerifyViewPublisheddata(){
        JSONObject jsonobj = new JSONObject(response.getBody().asString());
        assertEquals(jsonobj.getString("countryId"), SpecDataStore.get("Country3Code"));
        assertEquals(jsonobj.getString("currentYear"), String.valueOf(cm.getCurrentyear()));
        assertEquals(jsonobj.getString("dataAvailableForYear"),String.valueOf(cm.getCurrentyear()));
        assertEquals(jsonobj.getString("status"), "PUBLISHED");
        assertEquals(jsonobj.getString("status"), "PUBLISHED");
    }

    @Step("Verify the HealthIndicators scores are Displayed as expected")
    public void VerifyScore(){
        double totalScore = 0;
        int numScores = 0;
        double category_score = 0;
        double overall_Score = 0;
        int overallNumScore =0;

        String body = cm.getbody("country_data");
        JSONArray actualArray = new JSONObject(body).getJSONArray("healthIndicators");
        JSONArray jsonarray = new JSONObject(response.getBody().asString()).getJSONArray("categories");
        for (Object arr : jsonarray) {
            totalScore=0.0;
            numScores=0;;
            JSONObject jsonObj = (JSONObject) arr;
            JSONArray indicatorsArray = jsonObj.getJSONArray("indicators");
            for (Object obj : indicatorsArray) {
                JSONObject scoreobj = (JSONObject) obj;
                int score = scoreobj.getInt("score");
                for (Object bodyobj : actualArray) {
                    JSONObject jsonbody = (JSONObject) bodyobj;
                    if(scoreobj.getInt("id") == jsonbody.getInt("indicatorId")) {
                        assertEquals(jsonbody.getInt("score"), score);
                        break;
                    }
                }
                String code = scoreobj.getString("code");
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(code);
                if (score != -1 && matcher.matches()) {
                    totalScore += score;
                    numScores++;
                }
            }
            if(numScores>=1) {
                category_score = totalScore / numScores;
                assertEquals(category_score,jsonObj.getDouble("overallScore"));
                assertEquals((int)Math.ceil(jsonObj.getDouble("overallScore")), jsonObj.getInt("phase"));
                overall_Score+=jsonObj.getDouble("overallScore");
                overallNumScore++;
            }
            else{
                assertEquals(-1.0,jsonObj.getDouble("overallScore"));
                assertEquals(-1,jsonObj.getInt("phase"));
            }
        }

        Double Countryphase = overall_Score/overallNumScore;
        JSONObject jobj =  new JSONObject(response.getBody().asString());
        assertEquals((int)Math.ceil(Countryphase),jobj.getInt("countryPhase"));

    }

}
