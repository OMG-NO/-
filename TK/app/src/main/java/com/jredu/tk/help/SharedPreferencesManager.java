package com.jredu.tk.help;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 昂首天下 on 2016/11/5.
 * SharedPreferences帮助类
 */

public class SharedPreferencesManager {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    public static SharedPreferences.Editor startWrite(Context context,String name){
        sharedPreferences=context.getSharedPreferences(name, Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        return editor;
    }

    public static SharedPreferences startRead(Context context,String name){
        sharedPreferences=context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    public static SharedPreferences.Editor saveString(String key,String value){
        editor.putString(key,value);
        return editor;
    }

    public static SharedPreferences.Editor saveInt(String key,Integer value){
        editor.putInt(key,value);
        return editor;
    }
    public static SharedPreferences.Editor saveBoolean(String key,Boolean value){
        editor.putBoolean(key,value);
        return editor;
    }

    public static SharedPreferences.Editor saveLong(String key,Long value){
        editor.putLong(key,value);
        return editor;
    }

    public static SharedPreferences.Editor saveFloat(String key,Float value){
        editor.putFloat(key,value);
        return editor;
    }
}
