package com.himi.himilog

import android.app.Application
import com.google.gson.Gson
import com.himi.loglibrary.HimiConsolePrinter
import com.himi.loglibrary.manage.HimiLogManager
import com.himi.loglibrary.LogConfig
import com.himi.loglibrary.interfaces.HimiLogPrinter

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        HimiLogManager.init(object : LogConfig() {
            override fun injectJsonParser(): JsonParser {
                return JsonParser { src -> Gson().toJson(src) }
            }

            override fun getGlobalTag(): String {
                return "MyApplication";
            }

            override fun enable(): Boolean {
                return true;
            }
        }, HimiConsolePrinter())
    }
}