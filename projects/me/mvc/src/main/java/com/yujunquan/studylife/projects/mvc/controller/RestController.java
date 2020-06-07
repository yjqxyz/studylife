package com.yujunquan.studylife.projects.mvc.controller;


import com.yujunquan.studylife.projects.mvc.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private HelloService helloService;

    @RequestMapping("/rest/{userID}/{userName}")
    public String rest(@RequestBody String str){

        helloService.sayBack(str);
        return str;
    }

}
