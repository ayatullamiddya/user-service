package com.prc.user_profile.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class StudentController {
    @GetMapping("hi")
    public String sendHi(){
        return "hello";
    }
}
