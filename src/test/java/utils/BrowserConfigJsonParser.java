package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

public class BrowserConfigJsonParser {
    public static JSONObject parser(File file){
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try{
            Object object = parser.parse(new FileReader(file.getAbsolutePath()));
            jsonObject =  (JSONObject)object;
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }
}
