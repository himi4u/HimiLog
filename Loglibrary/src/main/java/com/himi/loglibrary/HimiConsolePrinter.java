package com.himi.loglibrary;

import android.util.Log;

import androidx.annotation.NonNull;

import com.himi.loglibrary.interfaces.HimiLogPrinter;

import static com.himi.loglibrary.LogConfig.MAX_LINE_LENGTH;

/**
 * 控制台打印器
 */
public class HimiConsolePrinter implements HimiLogPrinter {
    @Override
    public void print(@NonNull LogConfig config, int level, String tag, @NonNull String printString) {
        int length = printString.length();
        int countOfSub = length / MAX_LINE_LENGTH;
        //打印的字符串长度除一行中最多容纳的字符数
        if (countOfSub > 0) {
            //如果大于0，多于1行
            int currentLen = 0;
            for (int i = 0; i < countOfSub; i++) {
                //打印每一行的日志
                Log.println(level, tag, printString.substring(currentLen, currentLen + MAX_LINE_LENGTH));
                currentLen+=MAX_LINE_LENGTH;
            }
            if (currentLen != length) {
                //如果两者不相等，说明有不能整除的情况，则需要把剩余的打印
                Log.println(level, tag, printString.substring(currentLen, length));
            }
        } else {
            //1行以内
            Log.println(level, tag, printString);
        }
    }
}
