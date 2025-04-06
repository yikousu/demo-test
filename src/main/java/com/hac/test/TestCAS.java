package com.hac.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hac
 * @date 2025/4/6
 */
public class TestCAS {
    static final ExecutorService POOL = Executors.newFixedThreadPool(10);

    /**
     * 简单的CAS使用示例
     *
     * @param args
     */
    public static void main1(String[] args) {
        AtomicBoolean flag = new AtomicBoolean(false);

        // 尝试将flag从false设置为true
        boolean success = flag.compareAndSet(false, true);
        System.out.println("CAS操作是否成功: " + success);  // 输出true

        // 再次尝试同样的操作
        success = flag.compareAndSet(false, true);
        System.out.println("CAS操作是否成功: " + success);  // 输出false
    }

    /***
     * AtomicInteger 的特点：
     * 是 java.util.concurrent.atomic 包下的一个类，专门为并发环境设计。
     * 提供了原子操作（如 compareAndSet、incrementAndGet 等），能在多线程环境下保证线程安全。
     * 内部使用 CAS（Compare-And-Swap）机制，基于硬件级别的原子指令实现无锁操作。
     */
    public static void main(String[] args) throws InterruptedException {
        // AtomicInteger 保证了内存可见性（使用 volatile 语义） cnt 底层是有volatile修饰的
        // 不能直接用 Integer 替换 AtomicInteger，否则会失去线程安全性和原子性。
        AtomicInteger cnt = new AtomicInteger(0);
        for (int i = 0; i < 10; i++) {
            POOL.submit(() -> {
                int oldValue;
                int newValue;
                do {
                    oldValue = cnt.get();
                    newValue = oldValue + 1;
                } while (!cnt.compareAndSet(oldValue, newValue)); // CAS操作

                // 上面的循环可以用下面的简单方法替代
                // cnt.incrementAndGet();
            });
        }
        Thread.sleep(1000);
        System.out.println("结果:" + cnt.get());
        POOL.shutdown();
    }
}
