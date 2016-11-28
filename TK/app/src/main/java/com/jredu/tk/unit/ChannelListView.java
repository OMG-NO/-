package com.jredu.tk.unit;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/11/15.
 * 重写ListView，ListView和ScrollView的冲突
 */
public class ChannelListView extends ListView {
    public ChannelListView(Context context) {
        super(context);
    }

    public ChannelListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChannelListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
