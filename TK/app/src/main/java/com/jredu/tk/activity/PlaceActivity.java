package com.jredu.tk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jredu.tk.R;
import com.jredu.tk.adapter.PlaceAdapter;
import com.jredu.tk.datamanager.PlaceDataManager;

import java.util.ArrayList;
import java.util.List;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 选择高考地区的Activity
 */
public class PlaceActivity extends SwipeBackActivity {
    //返回
    private ImageView img_back;
    //上左右拉删除服务
    private SwipeBackLayout mSwipeBackLayout;
    //当前选择的地区
    private TextView tv_currentPlace;
    //所有地区的GrideView
    private GridView gv;
    //所有地区数据
    private List<String> mData;
    //显示所有地区的Adapter
    private PlaceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        //设置滑动方向
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeSize(200);
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        img_back = (ImageView) findViewById(R.id.img_back);
        tv_currentPlace = (TextView) findViewById(R.id.tv_currentPlace);
        gv = (GridView) findViewById(R.id.gv);
        tv_currentPlace.setText(getIntent().getStringExtra("currentPlace"));
        initData();
        adapter = new PlaceAdapter(mData, this);
        gv.setAdapter(adapter);
        //让GridView里当前地区被选中
        for (int i=0;i<mData.size();i++){
            if (tv_currentPlace.equals(mData.get(i))){
                gv.getAdapter().getItem(i);
            }
        }
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tv_currentPlace.setText(mData.get(i));
                finish();
            }
        });
    }

    //提供所有地区数据
    private void initData() {
        mData = new ArrayList<>();
        mData = PlaceDataManager.initData();
    }

    @Override
    public void finish() {
        Intent data=new Intent();
        data.putExtra("place",tv_currentPlace.getText());
        setResult(2,data);
        super.finish();
    }
}
