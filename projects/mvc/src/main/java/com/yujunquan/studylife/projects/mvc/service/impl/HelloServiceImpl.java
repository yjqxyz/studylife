package com.yujunquan.studylife.projects.mvc.service.impl;


import com.yujunquan.studylife.projects.mvc.dao.HelloDao;
import com.yujunquan.studylife.projects.mvc.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl  implements HelloService {

    @Autowired
    private HelloDao helloDao;

    public String sayBack(String content){
        content ="你说的内容："+ helloDao.sayBack(content) + "--经过service 处理 ";
        return content;
    }

}
