package com.jredu.tk.control;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

/**
 * Created by HunBing on 2016/11/15.
 */

public class GetActivityEvent {
    private static final String TAG=GetActivityEvent.class.getSimpleName();

    public static boolean getScrollingDistance(Context context, MotionEvent event){
//        int startX,startY,endX,endY;

        GestureDetector gestureDetector=new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Log.i(TAG,"距离"+distanceX+"   距离"+distanceY);

                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        });
        gestureDetector.onTouchEvent(event);

        return true;
    }
}

