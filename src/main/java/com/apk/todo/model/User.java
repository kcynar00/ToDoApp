package com.apk.todo.model;

public class User {


    private static int userID;
    private String name;
    private String login;
    private String password;

    public User() {
    }

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
