package com.yujunquan.studylife.dao.impl;

import com.yujunquan.studylife.dao.HelloDao;
import org.springframework.stereotype.Repository;

@Repository
public class HelloDaoImpl implements HelloDao {

    public String sayBack(String content){
        content += " --经过 dao 处理";
        return content;
    }
}
