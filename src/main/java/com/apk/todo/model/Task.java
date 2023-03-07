package com.apk.todo.model;

public class Task {
    private String data;
    private int dataid;
    private int userid;

    public int getDataid() {
        return dataid;
    }

    public void setDataid(int dataid) {
        this.dataid = dataid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Task() {
    }
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
