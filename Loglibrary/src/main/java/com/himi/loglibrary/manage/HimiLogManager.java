package com.himi.loglibrary.manage;

import com.himi.loglibrary.LogConfig;
import com.himi.loglibrary.interfaces.HimiLogPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HimiLogManager {

    private static HimiLogManager instance;
    private static LogConfig mConfig;
    //保存打印器
    private List<HimiLogPrinter> mPrinters = new ArrayList<>();

    private HimiLogManager(LogConfig config, HimiLogPrinter[] printers) {
        this.mConfig = config;
        this.mPrinters.addAll(Arrays.asList(printers));
    }

    public static HimiLogManager getInstance() {
        return instance;
    }

    public static void init(LogConfig config, HimiLogPrinter... printers) {
        instance = new HimiLogManager(config,printers);
    }

    public LogConfig getConfig() {
        return mConfig;
    }

    public List<HimiLogPrinter> getPrinters() {
        return mPrinters;
    }

    public void addPrinters(HimiLogPrinter printer) {
        mPrinters.add(printer);
    }
    public void removePrinter(HimiLogPrinter printer){
        mPrinters.remove(printer);
    }
}
