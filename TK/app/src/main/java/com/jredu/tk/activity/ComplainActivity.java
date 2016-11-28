package com.jredu.tk.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jredu.tk.R;
import com.jredu.tk.adapter.ComplainAdapter;
import com.jredu.tk.dao.ComplainDao;
import com.jredu.tk.entity.Compalin;
import com.jredu.tk.entity.User;
import com.jredu.tk.help.ActivityManager;
import com.jredu.tk.help.DateFormatHelper;

import java.util.ArrayList;
import java.util.List;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 吐槽Activity
 * by：du
 */
public class ComplainActivity extends SwipeBackActivity {
    //上左右拉删除服务
    private SwipeBackLayout mSwipeBackLayout;
    //用户输入的吐槽列表
    private ListView lv;
    //返回按钮
    private ImageView img_back;
    //输入吐槽内容的EditText
    private EditText et_complain;
    //发送字样
    private TextView tv_send;
    //显示Adapter
    private ComplainAdapter complainAdapter;
    //显示的吐槽数据
    private List<Compalin> mData;
    //吐槽专用Dao
    private ComplainDao dao;
    //用户
    private User user;
    //吐槽
    private Compalin compalin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);
        ActivityManager.getInstance().addActivity(this);
        //设置滑动方向
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeSize(200);
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        dao = new ComplainDao(this);
        lv = (ListView) findViewById(R.id.lv);
        img_back = (ImageView) findViewById(R.id.img_back);
        et_complain = (EditText) findViewById(R.id.et_complain);
        tv_send = (TextView) findViewById(R.id.tv_send);
        initData();
        complainAdapter = new ComplainAdapter(this, mData);
        lv.setAdapter(complainAdapter);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        et_complain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //发送字样是否出现
                if (i < i1) {
                    tv_send.setVisibility(View.GONE);
                } else {
                    tv_send.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //添加数据，加载数据
                compalin = new Compalin();
                compalin.setText(et_complain.getText().toString());
                compalin.setDate(DateFormatHelper.dateFormat());
                compalin.setAccount(user.getAccount());
                //加入本地数据库，加入界面，刷新界面
                dao.addComplain(compalin);
                mData.add(compalin);
                complainAdapter.notifyDataSetChanged();
                et_complain.setText(null);
            }
        });
    }

    //插入数据
    private void initData() {
        user = getIntent().getParcelableExtra("user");
        Compalin c=new Compalin();
        c.setAccount(user.getAccount());
        mData = new ArrayList<>();
        mData.addAll(dao.findComplainsByAccount(c));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().removeActivity(this);
    }
}
