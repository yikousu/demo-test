package com.hac.test;

import java.util.concurrent.*;

/**
 * @author hac
 * @date 2025/4/6 9:04
 */
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("当前线程:" + Thread.currentThread().getName());
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("当前线程:" + Thread.currentThread().getName());
    }
}

class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("当前线程:" + Thread.currentThread().getName());
        return "callable";
    }
}

public class TestThread {
    /**
     * Thread
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main1(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        // 新开一个线程
        thread.start();
        System.out.println(Thread.currentThread().getName());
        // 等待子线程执行完毕
        Thread.sleep(1000);
    }

    /**
     * Runnable
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main2(String[] args) throws InterruptedException {
        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        // 新开一个线程
        thread.start();
        System.out.println(Thread.currentThread().getName());
        // 等待子线程执行完毕
        Thread.sleep(1000);
    }

    /**
     * Callable
     *
     * @param args
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void main3(String[] args) throws InterruptedException, ExecutionException {
        MyCallable callable = new MyCallable();
        FutureTask<String> task = new FutureTask<>(callable);
        Thread thread = new Thread(task);
        // 新开一个线程
        thread.start();
        System.out.println("结果:" + task.get()); // 阻塞获取结果  不会继续往下执行
        System.out.println(Thread.currentThread().getName());
        // 等待子线程执行完毕
        Thread.sleep(1000);
    }

    /**
     * 底层也是通过ThreadPoolExecutor的构造函数方式创建【存在问题:OOM 因为LinkedBlockingQueue无界】
     */
    private static final ExecutorService pool = Executors.newFixedThreadPool(10);

    /**
     * 配合线程池
     *
     * @param args
     */
    public static void main(String[] args) {
        // Runnable
        pool.submit(() -> {
            System.out.println("当前线程:" + Thread.currentThread().getName());
        });
        // 可接收 Runnable 或 Callable
        pool.submit(new MyRunnable());
        pool.submit(new MyCallable());
        // 只能接收 Runnable
        pool.execute(new MyRunnable());

        pool.shutdown(); // 关闭线程池，程序会正常退出
    }

}
