package com.himi.loglibrary;

import android.util.Log;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class HimiLogType {
    @IntDef({e,i,w,d,v,a})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE{

    }
    public static final int e = Log.ERROR;
    public static final int i = Log.INFO;
    public static final int w = Log.WARN;
    public static final int d = Log.DEBUG;
    public static final int v = Log.VERBOSE;
    public static final int a = Log.ASSERT;
}
