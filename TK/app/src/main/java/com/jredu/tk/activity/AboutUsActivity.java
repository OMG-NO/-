package com.jredu.tk.activity;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jredu.tk.R;
import com.jredu.tk.help.ActivityManager;
import com.jredu.tk.help.SaveImg;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 关于我们Activity
 * by：du
 */
public class AboutUsActivity extends SwipeBackActivity {

    //上左右拉删除服务
    private SwipeBackLayout mSwipeBackLayout;
    //返回按钮
    private ImageView img_back;
    //关于我们二维码
    private ImageView us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ActivityManager.getInstance().addActivity(this);
        //设置滑动方向
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeSize(200);
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        img_back = (ImageView) findViewById(R.id.img_back);
        us = (ImageView) findViewById(R.id.us);
        us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                us.setVisibility(View.INVISIBLE);
                View v = AboutUsActivity.this.getLayoutInflater().inflate(R.layout.bigimg, null);
                final PopupWindow popupWindow = new PopupWindow(v,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT, true);
                popupWindow.setAnimationStyle(R.style.mine_pop);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(0x55000000));
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                ImageView img = (ImageView) v.findViewById(R.id.us);
                img.setImageDrawable((us.getDrawable()));
                RelativeLayout rl = (RelativeLayout) v.findViewById(R.id.rl);
                rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                        us.setVisibility(View.VISIBLE);
                        us.setMaxHeight(80);
                        us.setMaxWidth(80);
                        us.setMinimumWidth(80);
                        us.setMinimumHeight(80);
                        us.setScaleType(ImageView.ScaleType.FIT_XY);
                    }
                });
                img.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        SaveImg.saveImageToGallery(AboutUsActivity.this,((BitmapDrawable)us.getDrawable()).getBitmap());
                        Toast.makeText(AboutUsActivity.this,"图片已保存！",Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                        us.setVisibility(View.VISIBLE);
                        us.setScaleType(ImageView.ScaleType.FIT_XY);
                        us.setMaxHeight(80);
                        us.setMaxWidth(80);
                        us.setMinimumWidth(80);
                        us.setMinimumHeight(80);
                        return true;
                    }
                });
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().removeActivity(this);
    }
}
