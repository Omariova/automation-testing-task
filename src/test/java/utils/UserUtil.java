package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserUtil {
    public static boolean validFirstName(String firstName){
        return Character.isUpperCase(firstName.charAt(0));
    }

    public static boolean validLastName(String firstName, String lastName){
        return !lastName.equals(firstName) && Character.isUpperCase(lastName.charAt(0));
    }

    public static boolean validMobileNumber(String mobile){
        Pattern p = Pattern.compile("(\\+\\d)[0-9]{11}");
        Matcher m = p.matcher(mobile);
        return (m.find() && m.group().equals(mobile));
    }

    public static boolean validEmail(String email){
        String pattern = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                         "[a-zA-Z0-9_+&*-]+)*@"+
                         "(?:[a-zA-Z0-9-]+\\.)+[a-z"+
                         "A-Z]{2,7}$";
        Pattern p = Pattern.compile(pattern);
        return p.matcher(email).matches();
    }

    public static boolean validPassword(String password){
        Pattern p = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z]).+$");
        return p.matcher(password).matches();
    }

    public static User readUserData(String filePath){
        JSONParser parser = new JSONParser();
        User user = new User();
        try{
            Object object = parser.parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) object;
            user.setFirstName(jsonObject.get("firstName").toString());
            user.setLastName(jsonObject.get("lastName").toString());
            user.setEmail(jsonObject.get("email").toString());
            user.setPassword(jsonObject.get("password").toString());
            user.setMobile(jsonObject.get("mobile").toString());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return user;
    }
}
