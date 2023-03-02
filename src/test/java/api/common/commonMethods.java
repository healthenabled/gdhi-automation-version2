package api.common;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

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
}
