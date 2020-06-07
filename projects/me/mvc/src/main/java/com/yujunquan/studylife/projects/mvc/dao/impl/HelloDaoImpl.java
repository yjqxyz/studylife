package com.yujunquan.studylife.projects.mvc.dao.impl;


import com.yujunquan.studylife.projects.mvc.dao.HelloDao;
import org.springframework.stereotype.Repository;

@Repository
public class HelloDaoImpl implements HelloDao {

    public String sayBack(String content){
        content += " --经过 dao 处理";
        return content;
    }
}
