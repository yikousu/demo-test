package com.hac.test;

import java.io.IOException;

/**
 * @author hac
 * @date 2025/4/11 16:02
 */
public class TestRuntime {
    public static void main(String[] args) throws IOException {
        // 通过饿汉式创建 Runtime 对象
        Runtime runtime = Runtime.getRuntime();
        // 执行系统命令
        Process process = runtime.exec("notepad.exe");

        // 获取可用的处理器数量
        int processors = runtime.availableProcessors();
        System.out.println("可用处理器数量: " + processors);
    }
}
