package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

public class TestDataJsonParser {
    public static JSONObject validUser(){
        return (JSONObject) getJsonTestData().get("validUser");
    }

    public static JSONObject notValidUser(){
        return (JSONObject) getJsonTestData().get("notValidUser");
    }

    private static JSONObject getJsonTestData(){
        JSONObject jsonObject = null;
        File file = new File("src/test/java/tests/test_data/data.json");
        JSONParser parser = new JSONParser();
        User user = new User();
        try{
            Object object = parser.parse(new FileReader(file.getAbsoluteFile()));
            jsonObject = (JSONObject) object;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
