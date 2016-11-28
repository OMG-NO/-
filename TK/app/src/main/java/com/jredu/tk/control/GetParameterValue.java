package com.jredu.tk.control;

import android.util.Log;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by HunBing on 2016/11/9.
 */

public class GetParameterValue {
    /**
     *
     * @param bean
     * @param beanObj
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static HashMap<String,String> getParamValue(Class<?> bean,Object beanObj) throws IllegalArgumentException, IllegalAccessException{
        Field[] fs=bean.getDeclaredFields();
        HashMap<String,String> paramValue=new HashMap<>();
        for(int i=0;i<fs.length;i++){
            Field f=fs[i];
            f.setAccessible(true);
            Object val=f.get(beanObj);
            Log.i("方法名",f.getName());
            if (canContinue(f.getName()) && val!=null) {
                paramValue.put(f.getName(), val.toString());
            }
        }

        return paramValue;
    }

    private static boolean canContinue(String fieldName){
        switch (fieldName){
            case "$change":
                return false;
            case "CREATOR":
                return false;
            case "serialVersionUID":
                return false;
            case "requestURL":
                return false;
        }
        return true;
    }
}
