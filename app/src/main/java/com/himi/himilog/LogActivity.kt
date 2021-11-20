package com.himi.himilog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.himi.loglibrary.HimiLog
import com.himi.loglibrary.HimiLogType
import com.himi.loglibrary.LogConfig

class LogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)
    }

    fun onLog(view: View) {
        //自定义log
        HimiLog.log(object : LogConfig(){
            override fun isIncludeThread(): Boolean {
                return true
            }

            override fun stackTraceDepth(): Int {
                return 0
            }
        },HimiLogType.e,"himi","哈哈哈")
        HimiLog.i("嘟嘟嘟嘟嘟")
    }
}