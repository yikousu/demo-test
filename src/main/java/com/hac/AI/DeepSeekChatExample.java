package com.hac.AI;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.time.Duration;

public class DeepSeekChatExample {

    private static final String API_KEY = "sk-c4ad6dc3ce5f4a7b9cc015106e2596d5";
    private static final String BASE_URL = "https://api.deepseek.com/v1/chat/completions";

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(Duration.ofSeconds(60)) // 不设置容易超时
                .build();

        // 构建 messages 数组
        JSONArray messages = new JSONArray();

        JSONObject userMsg = new JSONObject();
        userMsg.put("role", "user");
        userMsg.put("content", "给我用java快速排序算法的代码");
        messages.add(userMsg);

        // 构建请求体
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "deepseek-chat");
        requestBody.put("messages", messages);
        requestBody.put("stream", false);

        // 构建 HTTP 请求
        Request request = new Request.Builder()
                .url(BASE_URL)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(
                        requestBody.toJSONString(),
                        MediaType.parse("application/json")))
                .build();


        // 发送请求
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                JSONObject jsonResponse = JSONObject.parseObject(responseBody);
                String content = jsonResponse
                        .getJSONArray("choices")
                        .getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content");
                System.out.println(content);
            } else {
                System.err.println("Request failed: " + response.code() + " " + response.message());
            }
        }
    }
}
