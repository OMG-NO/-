package com.jredu.tk.control;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jredu.tk.activity.AnswerActivity;
import com.jredu.tk.application.Constant;
import com.jredu.tk.entity.RequestBundle;


/**
 * Created by HunBing on 2016/11/11.
 */

public class GotoAnswerActivity {
    private static Bundle toAnswerActivitybundle;

    public static void jumpByCourse(String course, Context context){
        toAnswerActivitybundle=new Bundle();
        RequestBundle requestBundle=creatRequestBundle("1",course,"3");
        toAnswerActivitybundle.putParcelable("requestBundle",requestBundle);

        Intent intent=new Intent(context,AnswerActivity.class);
        intent.putExtra("requestBundle",toAnswerActivitybundle);
        intent.putExtra("url", Constant.findSubjectsByTypeAndCourse);
        context.startActivity(intent);
    }

    public static void jumpByTopical(String course,String topical,Context context){
        toAnswerActivitybundle=new Bundle();
        RequestBundle requestBundle=creatRequestBundle("1",course,topical);
        toAnswerActivitybundle.putParcelable("requestBundle",requestBundle);

        Intent intent=new Intent(context,AnswerActivity.class);
        intent.putExtra("requestBundle",toAnswerActivitybundle);
        intent.putExtra("url", Constant.findSubjectsByTopical);
        context.startActivity(intent);
    }

    public static void jumpByDegree(String course,String degree,Context context){
        toAnswerActivitybundle=new Bundle();
        RequestBundle requestBundle=creatRequestBundle("1",course);
        requestBundle.setDegree(degree);
        toAnswerActivitybundle.putParcelable("requestBundle",requestBundle);

        Intent intent=new Intent(context,AnswerActivity.class);
        intent.putExtra("requestBundle",toAnswerActivitybundle);
        intent.putExtra("url", Constant.findSubjectsByTypeAndCourseAndDegree);
        context.startActivity(intent);
    }

    private static RequestBundle creatRequestBundle(String type,String course){
        RequestBundle requestBundle=new RequestBundle();

        requestBundle.setType(type);
        requestBundle.setCourse(course);
        requestBundle.setCount("3");

        return requestBundle;
    }

    private static RequestBundle creatRequestBundle(String type,String course,String topical){
        RequestBundle requestBundle=new RequestBundle();

        requestBundle.setType(type);
        requestBundle.setCourse(course);
        requestBundle.setTopical(topical);
        requestBundle.setCount("3");

        return requestBundle;
    }
}
