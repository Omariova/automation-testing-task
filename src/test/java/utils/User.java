package utils;

import java.io.File;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String mobile;

    public static User getUserFromJsonFile(String filePath){
        File file = new File(filePath);
        return UserUtil.readUserData(file.getAbsolutePath());
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
