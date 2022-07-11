package com.example.food_donate;

public class User {
    String user_id;
    String name;
    String type;
    String mobile;
    String location;
    String password;

    public User()
    {

    }

    public User(String user_id, String name, String type, String mobile, String location, String password) {
        this.user_id = user_id;
        this.name = name;
        this.type = type;
        this.mobile = mobile;
        this.location = location;
        this.password = password;
    }
    public String getUser_id() { return user_id; }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getMobile() {
        return mobile;
    }

    public String getLocation() {
        return location;
    }

    public String getPassword() {
        return password;
    }
}
