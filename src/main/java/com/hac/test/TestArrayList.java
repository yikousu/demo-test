package com.hac.test;

import java.util.ArrayList;

/**
 * @author hac
 * @date 2025/4/18 12:29
 */
public class TestArrayList {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("test");
        /**
         * 错误
         */
        // Object[] toArray() - 返回一个 Object[] 数组
        String[] array1 = (String[]) list.toArray();
        /**
         * 正确
         */
        // <T> T[] toArray(T[] a) - 返回指定类型的数组
        String[] array2 = list.toArray(new String[0]);
        String[] array3 = list.toArray(new String[list.size()]);
    }
}
