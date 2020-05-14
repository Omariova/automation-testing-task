package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;

public class RegisterTrafficParser {

    public static void logHTTPRequest(String source, String destination, String endPoint){
        JSONObject request = null;
        JSONArray entries = getHarEntryData(source);
        Iterator<JSONObject> iterator = entries.iterator();
        while (iterator.hasNext()){
            request = (JSONObject)iterator.next().get("request");
            String url = request.get("url").toString();
            if(url.endsWith(endPoint)){
                writeHarEntryData(destination, request);
            }
        }

    }

    private static JSONArray getHarEntryData(String filePath){
        JSONParser parser = new JSONParser();
        JSONObject har , log = null;
        JSONArray entries = null;
        try{
            Object object = parser.parse(new FileReader(new File(filePath).getAbsoluteFile()));
            har = (JSONObject) object;
            log = (JSONObject) har.get("log");
            entries = (JSONArray) log.get("entries");


        }catch (Exception e){
            e.printStackTrace();
        }
        return entries;
    }

    private static void writeHarEntryData(String filePath, JSONObject json){
        FileWriter file = null;
        try{
            file = new FileWriter(filePath);
            file.write(json.toJSONString());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                file.flush();
                file.close();
            }catch (Exception e){e.printStackTrace();}

        }

    }

}
