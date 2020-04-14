package tests.login;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

public class LoginConfigJsonParser {
    private static JSONObject object = LoginConfigJsonParser.parser(new File("src/test/java/tests/Login/LoginConfig.json"));
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
    public static String validUserFileName(){
        return object.get("validUserData").toString();
    }
}
