package com.smileduster.vsboard.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String helloVsboard(String msg) {
        return msg + "!!!";
    }

    @GetMapping("/hello-user")
    public String helloUser() {
        Session session = SecurityUtils.getSubject().getSession();
        return (String) session.getAttribute("userNo");
    }

}
