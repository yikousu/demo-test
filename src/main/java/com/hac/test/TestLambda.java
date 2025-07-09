package com.hac.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hac
 * @date 2025/7/9 18:52
 */
public class TestLambda {
    // 当lambda表达式中引用外部变量时，外部变量不能被修改【①用final修饰 ②定义一个不修改的变量 ③不修改】
    // public static void main(String[] args) {
    //     List<Integer> list = new ArrayList<>();
    //     list.add(10);
    //     list.add(20);
    //     list.add(30);
    //
    //     int age = 20;
    //     list.stream().filter(i -> i > age).forEach(System.out::println);
    //     age = 50;
    //     System.out.println(age);
    // }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);

        int age = 20;
        int finalAge =age;
        list.stream().filter(i -> i > finalAge).forEach(System.out::println);
        age = 50;
        System.out.println(age);
    }
}
