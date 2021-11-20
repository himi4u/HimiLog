package com.himi.loglibrary;

import android.util.Log;

import androidx.annotation.NonNull;

import com.himi.loglibrary.interfaces.HimiLogPrinter;
import com.himi.loglibrary.manage.HimiLogManager;

import java.util.Arrays;
import java.util.List;

public class HimiLog {
    public static void v(Object... data) {
        vTag(HimiLogManager.getInstance().getConfig().getGlobalTag(), data);
    }

    public static void d(Object... data) {
        dTag(HimiLogManager.getInstance().getConfig().getGlobalTag(), data);
    }

    public static void e(Object... data) {
        eTag(HimiLogManager.getInstance().getConfig().getGlobalTag(), data);
    }

    public static void i(Object... data) {
        iTag(HimiLogManager.getInstance().getConfig().getGlobalTag(), data);
    }

    public static void w(Object... data) {
        wTag(HimiLogManager.getInstance().getConfig().getGlobalTag(), data);
    }

    public static void a(Object... data) {
        aTag(HimiLogManager.getInstance().getConfig().getGlobalTag(), data);
    }

    public static void vTag(String tag, Object... data) {
        log(HimiLogType.v, tag, data);
    }

    public static void dTag(String tag, Object... data) {
        log(HimiLogType.d, tag, data);
    }

    public static void eTag(String tag, Object... data) {
        log(HimiLogType.e, tag, data);
    }

    public static void iTag(String tag, Object... data) {
        log(HimiLogType.i, tag, data);
    }

    public static void wTag(String tag, Object... data) {
        log(HimiLogType.w, tag, data);
    }

    public static void aTag(String tag, Object... data) {
        log(HimiLogType.a, tag, data);
    }


    public static void log(@HimiLogType.TYPE int type, String tag, Object... data) {
        log(HimiLogManager.getInstance().getConfig(), type, tag, data);
    }

    public static void log(@NonNull LogConfig config, @HimiLogType.TYPE int type, String tag, Object... data) {
        if (!config.enable())
            return;
        StringBuilder sb = new StringBuilder();
        //先判断日志中是否包含线程信息
        if (config.isIncludeThread()) {
            String threadInfo = LogConfig.THREAD_FORMATTER.format(Thread.currentThread());
            sb.append(threadInfo);
            sb.append("\n");
        }
        //判断堆栈深度是否大于0，大于0显示堆栈信息
        if (config.stackTraceDepth() > 0) {
            String stackTraceInfo = LogConfig.STACK_TRACE_FORMATTER.format(new Throwable().getStackTrace());
            sb.append(stackTraceInfo);
            sb.append("\n");
        }
        String body = parseBody(data, config);
        sb.append(body);
        //获取打印器
        List<HimiLogPrinter> printers;
        HimiLogPrinter[] printerArray = config.printers();
        if (printerArray == null) {
            printers = HimiLogManager.getInstance().getPrinters();
        } else {
            printers = Arrays.asList(printerArray);
        }
        if (printers == null)
            return;
        //遍历打印器打印
        for (HimiLogPrinter printer : printers) {
            printer.print(config, type, tag, sb.toString());
        }
    }

    private static String parseBody(Object[] data, @NonNull LogConfig config) {
        //判断json序列化不为空的情况下
        if (config.injectJsonParser() != null) {
            return config.injectJsonParser().toJson(data);
        }
        StringBuilder sb = new StringBuilder();
        for (Object datum : data) {
            sb.append(datum.toString()).append(";");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
