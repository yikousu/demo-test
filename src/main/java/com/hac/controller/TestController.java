package com.hac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestController {
    @Autowired
    private TestService testService;

    public TestController() {
        System.out.println("TestController");
    }
}


