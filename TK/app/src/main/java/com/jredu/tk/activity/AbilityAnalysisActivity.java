package com.jredu.tk.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jredu.tk.R;
import com.jredu.tk.help.RadoView;

import java.util.LinkedHashMap;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 能力分析Activity
 */
public class AbilityAnalysisActivity extends SwipeBackActivity {
    //雷达图
    private RadoView rv;
    //上左右拉删除服务
    private SwipeBackLayout mSwipeBackLayout;
    //数据
    private LinkedHashMap<String, Integer> map;
    //返回
    private ImageView img_back;
    //最好的能力展示
    private TextView tv_best;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ability_analysis);
        img_back=(ImageView)findViewById(R.id.img_back);
        tv_best=(TextView)findViewById(R.id.tv_best);
        tv_best.setText("语文能力比较强");
        //设置滑动方向
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeSize(200);
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        rv = (RadoView) findViewById(R.id.rv);

        map = new LinkedHashMap<>();
        map.put("语文", 240);
        map.put("数学", 300);
        map.put("外语", 340);
        map.put("文科", 350);
        map.put("理科", 240);
        map.put("其他", 210);
        //数据的数量
        rv.setCount(map.size());
        //科目
        rv.setMap(map);
        //文字大小
        rv.setTextSize(50);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
