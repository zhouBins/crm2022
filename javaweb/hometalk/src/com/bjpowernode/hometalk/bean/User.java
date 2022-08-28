package com.bjpowernode.hometalk.bean;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private String userid;
    private String username;
    private String userpassword;
    private String usercall;

    public User() {

    }

    public User(String userid, String username, String userpassword, String usercall) {
        this.userid = userid;
        this.username = username;
        this.userpassword = userpassword;
        this.usercall = usercall;
    }


    @Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", userpassword='" + userpassword + '\'' +
                ", usercall='" + usercall + '\'' +
                '}';




    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUserid(), user.getUserid()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getUserpassword(), user.getUserpassword()) && Objects.equals(getUsercall(), user.getUsercall());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserid(), getUsername(), getUserpassword(), getUsercall());
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUsercall() {
        return usercall;
    }

    public void setUsercall(String usercall) {
        this.usercall = usercall;
    }
}
