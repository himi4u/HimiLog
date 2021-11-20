package com.himi.loglibrary.interfaces;

/**
 * 日志格式化
 */
public interface HimiLogFormatter<T> {
    /**
     * 无论何种数据类型，最后都被格式化为String类型
     *
     * @param data
     * @return
     */
    String format(T data);
}
