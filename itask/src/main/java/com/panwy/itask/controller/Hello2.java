package com.panwy.itask.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class Hello2 {
    
    @RequestMapping("/hello2")
    String test(final Model model){
        model.addAttribute("model", "User");
        return "hello2";
    }
}