package com.hac.controller;

import org.springframework.stereotype.Service;

/**
 * @author hac
 * @date 2025/3/31 8:49
 */
@Service
public class TestService {
    public TestService() {
        System.out.println("TestService init");
    }
    public void test() {
        System.out.println("TestService test");
    }
}
