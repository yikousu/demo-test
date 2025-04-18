package com.hac.test.design_patterns;

// 抽象处理者：定义处理请求的接口和下一个处理者的引用
abstract class Handler {
    // 指向责任链中的下一个处理者
    protected Handler next;

    // 设置下一个处理者
    public void setNext(Handler next) {
        this.next = next;
    }

    // 抽象方法：子类实现具体的处理逻辑
    public abstract void handleRequest(String request);
}

// 具体处理者：认证处理器
class AuthHandler extends Handler {
    @Override
    public void handleRequest(String request) {
        // 如果请求类型是 "auth"，当前处理者处理
        if ("auth".equals(request)) {
            System.out.println("AuthHandler 处理了请求");
        }
        // 否则将请求传递给下一个处理者
        else if (next != null) {
            next.handleRequest(request);
        }
    }
}

// 具体处理者：日志处理器
class LogHandler extends Handler {
    @Override
    public void handleRequest(String request) {
        // 如果请求类型是 "log"，当前处理者处理
        if ("log".equals(request)) {
            System.out.println("LogHandler 处理了请求");
        }
        // 否则将请求传递给下一个处理者
        else if (next != null) {
            next.handleRequest(request);
        }
    }
}

// 客户端：构建责任链并发起请求
public class Client {
    public static void main(String[] args) {
        // 创建两个处理器
        Handler auth = new AuthHandler();
        Handler log = new LogHandler();

        // 设置处理链顺序：auth -> log
        auth.setNext(log);

        // 发起一个请求，请求类型为 "log"
        // auth 先尝试处理，不匹配，传递给 log
        // log 匹配成功，处理请求
        auth.handleRequest("log");  // 输出：LogHandler 处理了请求
    }
}

