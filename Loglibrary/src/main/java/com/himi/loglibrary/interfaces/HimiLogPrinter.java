package com.himi.loglibrary.interfaces;

import androidx.annotation.NonNull;

import com.himi.loglibrary.LogConfig;

/**
 * 打印日志
 */
public interface HimiLogPrinter {
    /**
     *
     * @param config 配置信息
     * @param level 日志级别
     * @param tag tag
     * @param printString 打印内容
     */
    void print(@NonNull LogConfig config, int level, String tag, @NonNull String printString);
}
