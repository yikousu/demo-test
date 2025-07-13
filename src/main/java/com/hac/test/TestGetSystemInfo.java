package com.hac.test;

import java.util.List;

/**
 * @author hac
 * @date 2025/7/13 10:51
 */
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;
import oshi.util.Util;

import java.util.Arrays;

public class TestGetSystemInfo {
    public static void main(String[] args) {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        OperatingSystem os = systemInfo.getOperatingSystem();

        // 1. CPU 信息
        printCpuInfo(hardware.getProcessor());

        // 2. 内存
        printMemoryInfo(hardware.getMemory());

        // 3. 磁盘存储和分区
        printDiskInfo(hardware.getDiskStores());

        // 4. 网络接口统计
        printNetworkInfo(hardware.getNetworkIFs());

        // 5. 操作系统信息
        printOsInfo(os);

        // 6. 电池状态（笔记本）
        printBatteryInfo(hardware.getPowerSources());

        // 7. 传感器信息（温度、风扇等）
        printSensorsInfo(hardware.getSensors());
    }

    // 1. CPU 信息（使用率、负载、温度等）
    private static void printCpuInfo(CentralProcessor processor) {
        System.out.println("\n===== CPU 信息 =====");
        System.out.println("CPU 名称: " + processor.getProcessorIdentifier().getName());
        System.out.println("物理核心数: " + processor.getPhysicalProcessorCount());
        System.out.println("逻辑核心数: " + processor.getLogicalProcessorCount());
        System.out.println("最大频率: " + processor.getMaxFreq() / 1_000_000.0 + " GHz");

        // CPU 使用率（计算1秒内的变化）
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        Util.sleep(1000); // 等待1秒
        double cpuUsage = processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100;
        System.out.printf("当前 CPU 使用率: %.1f%%\n", cpuUsage);

        // CPU 温度（如果有）
        Sensors sensors = new SystemInfo().getHardware().getSensors();
        double cpuTemp = sensors.getCpuTemperature();
        if (cpuTemp > 0) {
            System.out.printf("CPU 温度: %.1f°C\n", cpuTemp);
        }
    }

    // 2. 内存和交换空间使用情况
    private static void printMemoryInfo(GlobalMemory memory) {
        System.out.println("\n===== 内存信息 =====");
        System.out.printf("总内存: %s\n", FormatUtil.formatBytes(memory.getTotal()));
        System.out.printf("可用内存: %s\n", FormatUtil.formatBytes(memory.getAvailable()));
        System.out.printf("内存使用率: %.1f%%\n", (1 - (double) memory.getAvailable() / memory.getTotal()) * 100);
    }

    // 3. 磁盘存储和分区信息
    private static void printDiskInfo(List<HWDiskStore> diskStores) {
        System.out.println("\n===== 磁盘信息 =====");
        for (HWDiskStore disk : diskStores) {
            System.out.println("磁盘名称: " + disk.getName());
            System.out.println("型号: " + disk.getModel());
            System.out.printf("容量: %s\n", FormatUtil.formatBytes(disk.getSize()));
            System.out.println("---");
        }
    }

    // 4. 网络接口统计
    private static void printNetworkInfo(List<NetworkIF> networkIFs) {
        System.out.println("\n===== 网络接口 =====");
        for (NetworkIF net : networkIFs) {
            System.out.println("接口名称: " + net.getName());
            System.out.println("MAC 地址: " + net.getMacaddr());
            System.out.printf("接收: %s\n", FormatUtil.formatBytes(net.getBytesRecv()));
            System.out.printf("发送: %s\n", FormatUtil.formatBytes(net.getBytesSent()));
            System.out.println("---");
        }
    }

    // 5. 操作系统版本和运行时间
    private static void printOsInfo(OperatingSystem os) {
        System.out.println("\n===== 操作系统信息 =====");
        System.out.println("操作系统: " + os.getFamily() + " " + os.getVersionInfo());
        System.out.println("系统启动时间: " + FormatUtil.formatElapsedSecs(os.getSystemBootTime()));
    }

    // 6. 电池状态（笔记本）
    private static void printBatteryInfo(List<PowerSource> powerSources) {
        System.out.println("\n===== 电池状态 =====");
        if (powerSources.isEmpty()) {
            System.out.println("未检测到电池（台式机？）");
            return;
        }
    }

    // 7. 传感器信息（温度、风扇速度等）
    private static void printSensorsInfo(Sensors sensors) {
        System.out.println("\n===== 传感器信息 =====");
        System.out.printf("CPU 温度: %.1f°C\n", sensors.getCpuTemperature());
        System.out.printf("风扇转速: %s RPM\n", Arrays.toString(sensors.getFanSpeeds()));
    }
}