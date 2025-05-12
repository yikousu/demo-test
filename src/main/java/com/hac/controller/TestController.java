package com.hac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api")
@RestController
public class TestController {
    @Autowired
    private TestService testService;

    public TestController() {
        System.out.println("TestController");
    }

    @GetMapping()
    public String test() {
        return "hello";
    }

}


