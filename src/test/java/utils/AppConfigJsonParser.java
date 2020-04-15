package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

public class AppConfigJsonParser {

    private static JSONObject appConfigJson = parse();

    public static String url(){
        return appConfigJson.get("url").toString();
    }

    public static JSONObject drivers(){
        return (JSONObject) appConfigJson.get("drivers");
    }
    private static JSONObject parse(){
        File file = new File("src/test/java/AppConfig.json");
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
