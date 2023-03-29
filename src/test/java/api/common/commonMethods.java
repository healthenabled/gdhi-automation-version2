package api.common;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class commonMethods {

    public String getbody(String key) {
        JSONParser parser = new JSONParser();
        String json=null;
        try {
            Object obj = parser.parse(new FileReader("src/test/java/api/data/testdata.json"));
            JSONObject jsonObject = (JSONObject) obj;
            json = (String) jsonObject.get(key).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String getCurrentMonth(){
        Calendar cal = Calendar.getInstance();
        return new SimpleDateFormat("MMMM").format(cal.getTime());
    }

    public int getCurrentyear(){
        LocalDate currentDate = LocalDate.now();
        return currentDate.getYear();
    }
}
