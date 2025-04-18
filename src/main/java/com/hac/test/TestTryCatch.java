package com.hac.test;

/**
 * @author hac
 * @date 2025/4/18 15:55
 */
public class TestTryCatch {
    /**
     * try finally 中异常处理
     */
    public static void main1(String[] args) {
        try {
            System.out.println("try");
            throw new RuntimeException("try");
        } finally {
            System.out.println("finally");
            throw new RuntimeException("finally");// finally中的异常会覆盖try中的异常
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("try");
            throw new RuntimeException("try");
        } finally {
            System.out.println("finally");
            try {
                throw new RuntimeException("finally");
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
}
