package com.himi.loglibrary;

import com.himi.loglibrary.interfaces.HimiLogFormatter;

/**
 * 线程格式化器
 */
public class HimiThreadFormatter implements HimiLogFormatter<Thread> {
    /**
     * 返回线程名
     *
     * @param data
     * @return
     */
    @Override
    public String format(Thread data) {
        return "Thread: " + data.getName();
    }
}
