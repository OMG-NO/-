package com.jredu.tk.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.jredu.tk.R;


/**
 * Created by HunBing on 2016/11/4.
 * 回答问题的页面中 的题干展示页面
 */

public class AnswerQuestionFragment extends Fragment {
    View view;

    LinearLayout theQuestionTitle;
    TextView theQuestion;
    String title;
    ImageView zoom_btn;

    PopupWindow popupWindow;
    View popup_view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.answer_question_pageitem,null);
        setView();
        setTheQuestionTitle();
        setViewClickListener();
        return view;
    }


    public void setView(){
        theQuestion= (TextView) view.findViewById(R.id.theQuestion);
        theQuestionTitle= (LinearLayout) view.findViewById(R.id.questionTitle);
        zoom_btn= (ImageView) view.findViewById(R.id.zoom_btn);
    }

    public void setTheQuestionTitle(){
        theQuestion.setText(title);
    }

    /**
     * 设置Fragment中将要显示的题目题干
     * @param title
     */
    public void setTheQuestionTitle(String title){
        this.title=title;
    }

    /**
     * 设置View的点击事件,点击显示popupWindow
     */
    private void setViewClickListener(){
        zoom_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"dianji",Toast.LENGTH_SHORT).show();
                setPopupWindowManager();
            }
        });
    }


    private void setPopupWindowManager(){
        popup_view=LayoutInflater.from(getContext()).inflate(R.layout.question_zoom_popup,null);
        TextView popup_text= (TextView) popup_view.findViewById(R.id.questiontitle_popup);
        popup_text.setText(title);
        popupWindow=new PopupWindow(
                popup_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        );
        popupWindow.showAtLocation(view,Gravity.CENTER,0,0);
        popup_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
}
