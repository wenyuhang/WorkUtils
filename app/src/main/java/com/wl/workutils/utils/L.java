package com.wl.workutils.utils;

import android.util.Log;

/**
 * Created by wyh on 2017/9/21.
 * LOG 日志管理
 */

public class L {
    private static boolean tolog=false;
    public static void d(String tag, String msg){
        if(tolog){
            Log.d(tag,msg);
        }
    }
}
