package com.hac.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hac
 * @date 2025/4/18 16:05
 */
public class TestJSON {
        // 注意：3000L 变成了 3000，在 JSON 中没有 Long 类型的概念，只有数字（不带后缀）
        public static void main(String[] args) {
            Long idValue = 3000L;
            Map<String, Object> data = new HashMap<>(2);
            data.put("id", idValue);

            String jsonString = JSON.toJSONString(data);

            Map map = JSON.parseObject(jsonString, Map.class);
            Object idObj = map.get("id");
            System.out.println("反序列化的类型是否为Integer: " + (idObj instanceof Integer));

        }
}
