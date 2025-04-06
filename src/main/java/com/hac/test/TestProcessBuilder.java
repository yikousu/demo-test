package com.hac.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author hac
 * @date 2025/4/1 14:36
 */
public class TestProcessBuilder {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 执行 Windows 下的 dir 命令，Linux/macOS 需要改为 "ls"
        Process process = Runtime.getRuntime().exec("cmd /c dir");

        // 读取进程的标准输出
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        // 等待进程执行完毕
        int exitCode = process.waitFor();
        System.out.println("进程退出码：" + exitCode);

    }
}
