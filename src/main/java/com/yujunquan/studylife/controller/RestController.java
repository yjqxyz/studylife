package com.yujunquan.studylife.controller;

import com.yujunquan.studylife.entity.User;
import com.yujunquan.studylife.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private HelloService helloService;

    @RequestMapping("/rest/{userID}/{userName}")
    public String rest(@RequestBody User user){
        String str = "userID:"+user.getUserID() +"userName:"+ user.getUserName();
        helloService.sayBack(str);
        return str;
    }

}
