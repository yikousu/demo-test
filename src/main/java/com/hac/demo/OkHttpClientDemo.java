package com.hac.demo;

import com.alibaba.fastjson2.JSONObject;
import okhttp3.*;

import java.io.IOException;

/**
 * @author hac
 * @date 2025/6/6
 */
public class OkHttpClientDemo {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient.Builder()
                .hostnameVerifier((hostname, session) -> true) // 跳过主机名验证
                .build();

        Request request = new Request.Builder()
                .url("https://api.uomg.com/api/rand.qinghua")
                .header("User-Agent", "MyApp/1.0")  // 添加User-Agent
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response.code());
            }

            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                JSONObject jsonObject = JSONObject.parseObject(responseBody.string());
                System.out.println(jsonObject.get("content"));
            }
        } catch (IOException e) {
            System.err.println("Request failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
