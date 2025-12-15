package com.farm.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping
    public Object test(){
        Map<String ,String> object= new HashMap<>();
        object.put("name","Pratik");
        object.put("email","Pratik@gmail.com");
        return object;
    }
}
