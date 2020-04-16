package utils;

import org.json.simple.JSONObject;
import pages.LoginPage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String mobile;

    public User getUser(String type){
        JSONObject userJson = null;
        switch (type){
            case "validUser":
                userJson = TestDataJsonParser.validUser();
                break;
            case "notValidUser":
                userJson = TestDataJsonParser.notValidUser();
        }
        User user = new User();
        user.setFirstName(userJson.get("firstName").toString());
        user.setLastName(userJson.get("lastName").toString());
        user.setEmail(userJson.get("email").toString());
        user.setMobile(userJson.get("mobile").toString());
        user.setPassword(userJson.get("password").toString());
        return user;
    }
    public boolean isValidUser(){
        return this.isValidFirstName() && this.isValidLastName() && this.isValidEmail() && this.isValidMobileNumber() && this.isValidPassword();
    }
    public boolean isValidFirstName(){
        return Character.isUpperCase(this.firstName.charAt(0));
    }

    public boolean isValidLastName(){
        return !this.lastName.equals(this.firstName) && Character.isUpperCase(this.lastName.charAt(0));
    }

    public boolean isValidMobileNumber(){
        Pattern p = Pattern.compile("(\\+\\d)[0-9]{11}");
        Matcher m = p.matcher(this.mobile);
        return (m.find() && m.group().equals(this.mobile));
    }

    public boolean isValidEmail(){
        String pattern = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@"+
                "(?:[a-zA-Z0-9-]+\\.)+[a-z"+
                "A-Z]{2,7}$";
        Pattern p = Pattern.compile(pattern);
        return p.matcher(this.email).matches() && !TestDataJsonParser.isUserExist(this.email);
    }

    public boolean isValidPassword(){
        Pattern p = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z]).+$");
        return p.matcher(this.password).matches();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
