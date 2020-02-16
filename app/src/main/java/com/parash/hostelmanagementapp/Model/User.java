package com.parash.hostelmanagementapp.Model;

public class User {
    private String usename;
    private String password;
    private String image;

    public User() {
    }

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User(String usename, String password, String image) {
        this.usename = usename;
        this.password = password;
        this.image = image;
    }
}
