package com.panwy.itask.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
    //@Value("${my.user}")
    private String user;
     @Value("${my.pass}")
    private String pass;
    @RequestMapping("/hello")
    String test(){
        return "hello" + pass;
    }
}