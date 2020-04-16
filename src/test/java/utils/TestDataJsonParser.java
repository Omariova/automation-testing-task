package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.parser.Parser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;

public class TestDataJsonParser {
    public static JSONObject validUser(){
        return (JSONObject) getJsonTestData().get("validUser");
    }

    public static JSONObject notValidUser(){
        return (JSONObject) getJsonTestData().get("notValidUser");
    }

    public static JSONObject signUpAssertionValues(){
        return (JSONObject) getJsonTestData().get("signUpAssertionValues");
    }

    public static JSONObject loginAssertionValues(){
        return (JSONObject) getJsonTestData().get("loginAssertionValues");
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

    public static JSONArray readUserEmails(){
        File file = new File("src/test/java/tests/test_data/users.json");
        JSONArray users = null;
        JSONParser parser = new JSONParser();
        if(file.isFile()){
            try{
                Object object = parser.parse(new FileReader(file));
                JSONObject jsonObject = (JSONObject) object;
                users = (JSONArray) jsonObject.get("users");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return users;
    }

    public static void addUserEmail(String email){
        File file = new File("src/test/java/tests/test_data/users.json");
        FileWriter fileWriter = null;
        JSONArray users; JSONObject object = new JSONObject();
        if (file.isFile()){
            users = readUserEmails();
            users.add(email);
            object.put("users", users);
            try {
                fileWriter = new FileWriter(file);
                fileWriter.write(object.toJSONString());
            }catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try{
                    fileWriter.flush();
                    fileWriter.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }else{
            try{
                users = new JSONArray();
                users.add(email);
                object.put("users", users);
                fileWriter = new FileWriter(file);
                fileWriter.write(object.toJSONString());
            }catch (Exception e){
                e.printStackTrace();
            }
            finally {
                try{
                    fileWriter.flush();
                    fileWriter.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean isUserExist(String email){
        JSONArray users = readUserEmails();
        Iterator<String> iterator = users.iterator();
        while (iterator.hasNext()){
            if(iterator.next().toLowerCase().equals(email.toLowerCase())){
                return true;
            }
        }
        return false;
    }

}
