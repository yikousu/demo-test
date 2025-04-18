package com.hac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hac
 * @date 2025/4/18 10:53
 */
public class TestArrays {

    public static void main1(String[] args) {
        // arr 是一个 基本类型数组（int 的数组） int[] arr (基本类型数组)
        // 当传入基本类型数组（如 int[]）时，整个数组被当作单个 T 对象（即 T 是 int[] 类型）。
        // 所以 list.size() 是 1，因为 List 中只有一个元素 —— 这个元素是整个 int[] 数组。
        int[] arr = {1, 2, 3};
        System.out.println(Arrays.asList(arr).size());// 1

        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        System.out.println(list.size()); //  3


        // arr2 是一个 引用类型数组（String 的数组） String[] arr2 (对象类型数组)
        // 当传入对象类型数组（如 String[]）时，数组的每个元素被当作 List 的一个元素（即 T 是 String 类型）。
        String[] arr2 = {"a", "b", "c"};
        System.out.println(Arrays.asList(arr2).size());// 3
    }

    public static void main2(String[] args) {
        String[] arr = {"a", "b", "c"};
        // Arrays.asList(arr) 大小固定（不能变长或变短）
        // Arrays.asList(arr) 内容可变（可以修改某个位置上的值）
        List<String> list = Arrays.asList(arr);
        // list.add("d"); //抱错
        System.out.println(list);

        List<String> list1 = new ArrayList<>(list);
        list1.add("d");
        System.out.println(list1);

        List<String> list2 = Arrays.stream(arr).collect(Collectors.toList());
        list2.add("dd");
        System.out.println(list2);

    }

    public static void main(String[] args) {
        String[] arr = {"a", "b", "c"};
        List<String> list = Arrays.asList(arr);
        System.out.println(list);
        arr[0]="aaa";
        System.out.println(list);

        // 想要真正的「独立 List」（不受原数组影响）怎么办？ 就要复制一份
        String[] arr1 = {"a", "b", "c"};
        ArrayList<String> list1 = new ArrayList<>(Arrays.asList(arr1));
        System.out.println(list1);
        arr[0]="aaa";
        System.out.println(list1);
    }
}
