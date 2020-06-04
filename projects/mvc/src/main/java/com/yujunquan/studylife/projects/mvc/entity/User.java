package com.yujunquan.studylife.projects.mvc.entity;

public class User {
    private Integer userID;
    private String userPwd;
    private String userName;
    private  UserInfo userInfo;

    public Integer getUserID() {
        return this.userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserInfo getUserInfo() {

        return this.userInfo;
    }

    public String getUserPwd() {
        return this.userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + this.userID +
                ", userPwd='" + this.userPwd + '\'' +
                ", userName='" + this.userName + '\'' +
                ", userInfo=" + this.userInfo +
                '}';
    }
}
