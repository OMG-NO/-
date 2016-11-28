package com.jredu.tk.control;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jredu.tk.datamanager.RequestManager;
import com.jredu.tk.entity.PracticeContent;
import com.jredu.tk.entity.QuestionJson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by HunBing on 2016/11/11.
 */

public class GetFragmentContent {
    private getResponseListener responseListener;

    public void setResponseListener(getResponseListener responseListener){
        this.responseListener=responseListener;
    }

    public void getPracticeContent(Context context,String course){
        HashMap<String,String> param=new HashMap<>();
        param.put("course",course);
        RequestManager.getInstance(context).requestAsyn(
                "courses/randomTopicals",
                RequestManager.TYPE_POST_JSON,
                param,
                new RequestManager.ReqCallBack<String>() {
                    @Override
                    public void onReqSuccess(String result) {
                        PracticeContent practiceContent=getPracticeContent(result);
                        responseListener.getResponseSuccess(practiceContent);
                    }

                    @Override
                    public void onReqFailed(String errorMsg) {
                        Log.i("主页面的网络请求","请求失败");
                    }
                }
        );
    }

    private PracticeContent getPracticeContent(String result){
        PracticeContent content=new PracticeContent();
        Gson gson=new Gson();
        JsonElement jsonElement=new JsonParser().parse(result);
        JsonArray jsonArray=null;
        if (jsonElement.isJsonArray()){
            jsonArray=jsonElement.getAsJsonArray();
        }
        Iterator it = jsonArray.iterator();
        List<String> pointList=new ArrayList<>();
        while (it.hasNext()) {
            JsonElement e = (JsonElement) it.next();
            //JsonElement转换为JavaBean对象
            Log.i("页面信息","e的内容"+e);
            String point=gson.fromJson(e,String.class);
            pointList.add(point);
        }
        content.setPointList(pointList);
        return content;
    }

    public interface getResponseListener{
        public void getResponseSuccess(PracticeContent result);
        public void getResponseError();
    }
}
