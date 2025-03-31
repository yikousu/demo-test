package com.hac.test;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author hac
 * @date 2025/3/31 16:38
 */
public class TestLang3 {
    public static void main(String[] args) {
        // isNotBlank
        if (StringUtils.isNotBlank("")) {
            System.out.println("not blank");
        } else {
            System.out.println("blank");
        }

        // reverse
        System.out.println(StringUtils.reverse("abc"));

        // NumberUtils
        System.out.println(NumberUtils.max(new int[]{1,2,3}));
        System.out.println(NumberUtils.toInt("123"));

        // RandomStringUtils
        System.out.println(RandomStringUtils.random(100)); //默认会生成由 ISO-8859-1 可打印字符 组成的随机字符串
        System.out.println(RandomStringUtils.randomAlphabetic(10));
    }
}
