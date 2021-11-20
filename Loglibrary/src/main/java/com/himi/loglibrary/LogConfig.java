package com.himi.loglibrary;

import com.himi.loglibrary.interfaces.HimiLogPrinter;

public abstract class LogConfig {
    // 1行显示的最大字符数
    protected static int MAX_LINE_LENGTH = 512;
    //线程格式化器
    static HimiThreadFormatter THREAD_FORMATTER = new HimiThreadFormatter();
    //堆栈格式化器
    static HimiStackTraceFormatter STACK_TRACE_FORMATTER = new HimiStackTraceFormatter();

    public String getGlobalTag() {
        return "HimiLog";
    }

    // 这里也可以用抽象的也可以不用抽象的，是因为如果在调用HimiLog.log()带config参数的方法的时候，也必须一定肯定实现一遍enable，如果全局配置的config的enabe和
    // 使用的时候配置的enable是不同的话这样就不能实现全局控制日志的功能了
    //    public abstract boolean enable();
    public boolean enable() {
        return true;
    }

    //对象序列化，将序列化交给调用者实现
    public interface JsonParser {
        String toJson(Object src);
    }

    //外界对json序列化器的注入
    public JsonParser injectJsonParser() {
        return null;
    }

    //日志中是否包含线程信息
    public boolean isIncludeThread() {
        return false;
    }

    //日志中显示堆栈信息的深度
    public int stackTraceDepth() {
        return 5;
    }

    //外界注册打印器
    public HimiLogPrinter[] printers() {
        return null;
    }
}
