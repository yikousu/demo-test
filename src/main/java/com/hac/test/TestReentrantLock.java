package com.hac.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁是用来 让多个线程互斥访问共享资源的，锁对象必须是共享的！！，否则就是“摆设”。【多个线程只有一个线程拿到锁了才能去访问共享资源】
 */
public class TestReentrantLock {
    private static final ExecutorService POOL = Executors.newFixedThreadPool(5);
    // 让所有线程共享同一把锁
    private static final ReentrantLock LOCK = new ReentrantLock();

    /**
     * cnt 可以不用 volatile 修饰，在加锁场景下其实可有可无（因为有锁，保证可见性）。
     */
    static int cnt = 0;

    /**
     * 加锁
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main1(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            POOL.submit(() -> {
                try {
                    addOneWithLock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        POOL.shutdown();
        POOL.awaitTermination(1, TimeUnit.SECONDS); // 等待任务执行完
        System.out.println("最终结果:" + cnt);
    }

    /**
     * 不用锁操作共享变量 每次cnt结果都不同
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main2(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            POOL.submit(() -> {
                try {
                    addOneWithOutLock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        POOL.shutdown();
        POOL.awaitTermination(1, TimeUnit.SECONDS); // 等待任务执行完
        System.out.println("最终结果:" + cnt);
    }

    /**
     * 用java.util.concurrent.atomic包下的原子类
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        int threads = 5;
        // 使用 CountDownLatch 确保主线程等待所有子线程完成
        CountDownLatch latch = new CountDownLatch(threads);
        // 原子类
        AtomicInteger count = new AtomicInteger(0);
        for (int i = 0; i < threads; i++) {
            POOL.submit(() -> {
                try {
                    count.incrementAndGet();
                } finally {
                    latch.countDown(); // 放到任务里面
                }
            });
        }
        latch.await();
        System.out.println(count);
        POOL.shutdown();
    }

    public static void addOneWithLock() throws InterruptedException {
        // 使用 tryLock(timeout) 可以避免死锁；你也可以直接用 LOCK.lock();
        boolean tryLock = LOCK.tryLock(2000, TimeUnit.MILLISECONDS);
        if (tryLock) {
            try {
                cnt++;
            } finally {
                LOCK.unlock();
            }
        }
    }

    public static void addOneWithOutLock() throws InterruptedException {
        cnt++;
    }
}
