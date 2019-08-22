package com.wl.workutils.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.wl.workutils.app.App;


/**
 * Created by wyh on 2017/9/27.
 * SharedPreferences   本地文件存储
 */

public class S {
    public static SharedPreferences sharedPreferences=  App.context.getSharedPreferences("yoawo", Context.MODE_PRIVATE);
    private static SharedPreferences.Editor editor=sharedPreferences.edit();
    /**
     * 存储String元素
     * @param key
     * @param value
     */
    public static void putDate(String key, String value){
        //"token"
        editor.putString(key,value);
        editor.commit();
    }

    /**
     * 获取String 元素
     * @param key
     * @return
     */
    public static String getString(String key){
        return sharedPreferences.getString(key,"");
    }


    /**
     * 存储boolean元素
     * @param key
     * @param value
     */
    public static void putDate(String key, boolean value){
        //"token"
        editor.putBoolean(key,value);
        editor.commit();
    }

    /**
     * 获取boolean元素
     * @param key
     * @return
     */
    public static boolean getBoolean(String key){
        return sharedPreferences.getBoolean(key,false);
    }
    /**
     * 清除全部数据
     */
    public static void deleteData(){
        editor.clear();
        editor.commit();
    }

    /**
     * 删除指定元素
     * @param key
     */
    public static void deleteData(String key){
        editor.remove(key);
        editor.commit();
    }
}
