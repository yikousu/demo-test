package com.hac.test;

import com.google.common.collect.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * @author hac
 * @date 2025/4/7 19:52
 */
public class TestGuava {
    /**
     * 集合
     *
     * @param args
     */
    public static void main(String[] args) {
        /**
         * Multimap (一个 key 对应多个 value)
         */
        ArrayListMultimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("水果", "苹果");
        multimap.put("水果", "香蕉");
        multimap.put("水果", "橙子");
        multimap.put("蔬菜", "胡萝卜");
        multimap.put("蔬菜", "菠菜");

        System.out.println(multimap.get("水果"));
        System.out.println(multimap.get("蔬菜"));


        /**
         * BiMap 支持键值反查的 Map（value 也唯一）
         */
        HashBiMap<String, Integer> biMap = HashBiMap.create();
        biMap.put("一", 1);

        System.out.println(biMap.get("一"));
        System.out.println(biMap.inverse().get(1));  // inverse 反转

        /**
         * Table 支持二维键的 Map（类似 Map<R, Map<C, V>>）
         * 二维映射：可以将数据存储为行和列的组合，类似于表格。
         * 灵活操作：提供了丰富的操作方法，如按行访问、按列访问、行列转换等。
         */
        Table<String, String, Integer> table = HashBasedTable.create();
        // 添加数据
        table.put("row1", "col1", 10);
        table.put("row1", "col2", 20);
        table.put("row2", "col1", 30);
        table.put("row2", "col3", 40);

        // 按行访问
        Map<String, Integer> row1 = table.row("row1");
        System.out.println(row1);

        // 按列访问
        Map<String, Integer> col1 = table.column("col1");
        System.out.println(col1);

        // 获取单个值
        Integer value = table.get("row1", "col2");
        System.out.println(value);

        /**
         * 不可变集合
         */

        ImmutableList<String> list = ImmutableList.of("红灯", "黄灯", "绿灯");
        System.out.println(list);

        ImmutableMap<String, String> immutableMap = ImmutableMap.of("张三", "男", "李四", "女");
        System.out.println(immutableMap);

        /**
         * Lists, Maps, Sets 工具类：简洁构造集合、切片、转换等
         */
        ArrayList<String> list1 = Lists.newArrayList("a", "b", "c");
        System.out.println(list1);

        // 集合工具
        Set<String> set1 = Sets.newHashSet("a", "b", "c");
        Set<String> set2 = Sets.newHashSet("b", "c", "d");
        Sets.SetView<String> union = Sets.union(set1, set2); // 并集
        System.out.println(union);

    }
}
