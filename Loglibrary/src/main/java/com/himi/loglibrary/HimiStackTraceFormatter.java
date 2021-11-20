package com.himi.loglibrary;

import com.himi.loglibrary.interfaces.HimiLogFormatter;

/**
 * 堆栈信息格式化器
 */
public class HimiStackTraceFormatter implements HimiLogFormatter<StackTraceElement[]> {
    /**
     * 堆栈信息格式化为String类型
     *
     * @param data 堆栈信息是数组类型的数据
     * @return
     */
    @Override
    public String format(StackTraceElement[] data) {
        StringBuilder sb = new StringBuilder(128);//初始化大小
        if (data == null || data.length == 0) {
            return null;
        }
        if (data.length == 1) {
            //横向制表符，有8位的缩进
            return "\t- " + data[0].toString();
        }
        for (int i = 0; i < data.length; i++) {
            if (i == 0) {
                //第0个先回车
                sb.append("stackTrace: \n");
            }
            if (i != data.length - 1) {
                //只要不是最后一个
                sb.append("\t ├ ");
                sb.append(data[i].toString());
                sb.append("\n");
            }
            if (i == data.length - 1) {
                //最后一个
                sb.append("\t └ ");
                sb.append(data[i].toString());
            }
        }
        return sb.toString();
    }
}
