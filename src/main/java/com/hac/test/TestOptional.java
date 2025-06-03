package com.hac.test;

import java.util.Optional;

/**
 * @author hac
 * @date 2025/6/3 21:38
 */
public class TestOptional {
    public static void main(String[] args) {
        Optional<String> opt1 = Optional.of("hello");
        Optional<Object> opt2 = Optional.empty();

        // 参数是消费型函数式接口
        opt1.ifPresent(value -> {
            System.out.println("不为空 对应值为:" + value);
        });
        opt2.ifPresent(value -> {
            System.out.println("不为空 对应值为:" + value);
        });

        // 如果为空，则返回默认值
        System.out.println(opt2.orElse("默认值"));

        //  如果为空，则返回默认值【供给型函数式接口返回一个值】
        System.out.println(opt2.orElseGet(() -> {
            return "hello";
        }));


        /**
         * 传统写法（易 NPE）
         */
        User user = null;
        String city = null;
        if (user != null && user.getAddress() != null) {
            city = user.getAddress().getCity();
        }
        System.out.println(city);
        /**
         *  优雅写法
         */
        String city1 = Optional.ofNullable(user)
                .map(User::getAddress)
                .map(Address::getCity)
                .orElse("未知城市");

        System.out.println(city1);

        Optional.ofNullable(user)
                .map(User::getAddress)
                .map(Address::getCity)
                .orElse("未知城市");

    }
}

class User {
    private Address address;

    public Address getAddress() {
        return address;
    }
}

class Address {
    private String city;

    public String getCity() {
        return city;
    }
}
