package com.hac.test;

import org.apache.commons.lang3.StringUtils;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 四种方法引用
 */
public class Test1 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        // 静态方法引用 【调用的是类的静态方法】
        // 等价于：name -> StringUtils.capitalize(name)
        names.stream().map(StringUtils::capitalize).forEach(System.out::println);


        // 实例方法引用【调用的是实例方法，且方法的调用对象由系统自动推导】
        // 等价于：name -> System.out.println(name)
        PrintStream out = System.out;
        names.stream().forEach(System.out::println);

        //  特定对象的实例方法引用【方法的调用者是某个特定的对象】
        // 等价于：name -> prefix.concat(name)
        String prefix = "Mr. "; // 特定对象的实例
        names.stream().map(prefix::concat).forEach(System.out::println);

        // 构造方法引用【需要根据旧对象创建新对象。】
        //  等价于：name -> new String(name)
        List<String> collect = names.stream().map(String::new).collect(Collectors.toList());
        System.out.println(collect);
    }
}
