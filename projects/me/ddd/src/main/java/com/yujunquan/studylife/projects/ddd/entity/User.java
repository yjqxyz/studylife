package com.yujunquan.studylife.projects.ddd.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    private Integer userID;
    private String userPwd;
    private String userName;
    private  UserInfo userInfo;

}
