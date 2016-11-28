package com.jredu.tk.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jredu.tk.R;
import com.jredu.tk.control.RequestQuestion;
import com.jredu.tk.entity.Question;
import com.jredu.tk.entity.RequestBundle;
import com.jredu.tk.fragment.AnswerMainFragment;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.List;

/**
 * Created by HunBing on 2016/11/3.
 */

public class AnswerActivity extends AppCompatActivity {
    private static final String TAG = "AnswerActivity";
    /**
     * 底部划出布局
     */
    private SlidingUpPanelLayout slidingLayout;
    //等待动画
    ImageView hintPic;

    //请求参数类
    RequestBundle requestBundle;
    //请求地址
    String url;

    //toolbar中的控件
    //当前题号
    TextView current_question;
    //总的题的数量
    TextView whole_question;
    //返回按键
    ImageView back_btn;

    //页面中的主要Fragment
    AnswerMainFragment answerFragment;

    //底部划出页面中详解内容
    TextView analysisContent;

    //取得返回的问题
    RequestQuestion requestQuestion;
    //所取到的问题列表
    List<Question> questionList;
    //答案
    private TextView answer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answerquestion);
        //取出传递的Intent数据
        requestBundle=getIntent().getBundleExtra("requestBundle").getParcelable("requestBundle");
        url=getIntent().getStringExtra("url");
        //将ActionBar替换为toolbar
        setSupportActionBar((Toolbar) findViewById(R.id.answer_toolbar));
        //取出页面View
        setView();
        //设置底部划出的View
        setSlidingLayout();
        //设置toolbar
        setToolbar();

        getResponseQuestion();

    }

    /**
     * 获取页面控件
     */
    public void setView(){
        slidingLayout= (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        analysisContent= (TextView) findViewById(R.id.analysis);
        answer=(TextView)findViewById(R.id.answer);
//        hintPic= (ImageView) findViewById(R.id.hintpic);
    }

    /**
     * 设置底部划出布局的属性
     */
    private void setSlidingLayout(){
        slidingLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i(TAG, "onPanelSlide, offset " + slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                Log.i(TAG, "onPanelStateChanged " + newState);
            }
        });
        slidingLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });
    }
    /**
     * 设置Toolbar中的事件
     */
    private void setToolbar(){
        back_btn= (ImageView) findViewById(R.id.back_btn);
        current_question= (TextView) findViewById(R.id.current_question);
        whole_question= (TextView) findViewById(R.id.whole_question);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 设置Fragment中的事件回调
     */
    private void setAnswerFragment(List<Question> questionList){
        answerFragment=new AnswerMainFragment();
        answerFragment.setTheQuestionList(questionList);
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.answer_mainfragment,answerFragment);
        transaction.commit();

        answerFragment.setFragmentListener(new onFragmentListener());
    }

    /**
     * 设置详细解析页面的内容
     */
    private void setAnalysisContent(int position){
        Log.i("详细资料下标越界","下表"+position);
        analysisContent.setText(questionList.get(position).getAnalysis());
        answer.setText(questionList.get(position).getAnswer());
    }

    /**
     * 取得通过{@class RequestQuestion} 类取得的题目数据,并且返回
     */
    public void getResponseQuestion() {
        requestQuestion=new RequestQuestion();
        Log.i("Activity","requestBundle"+requestBundle.getCourse());
        requestQuestion.getRequestQuestion(this,requestBundle,requestBundle.getClass(),url);
        requestQuestion.setRequestSuccessListener(new RequestQuestion.OnRequestSuccessListener() {
            @Override
            public void getReponseQuestion(List<Question> question) {
                Log.i("请求数据",question.get(0).getOptionList().size()+"数据长度");

                questionList=question;

                whole_question.setText(questionList.size()+"");
                setAnalysisContent(0);
                setAnswerFragment(questionList);
            }
        });
    }

    public class onFragmentListener implements AnswerMainFragment.OnFragmentListener{

        @Override
        public void onQuestionPageStateChanged(int state) {

        }

        @Override
        public void onQuestionPageSelected(int position) {
            current_question.setText(String.valueOf(position+1));
            setAnalysisContent(position);
        }

        @Override
        public void onChooseAnswerLinstener(int position, int answerPosition) {
            String rightAnswer=questionList.get(position).getAnswer();

            int rigthAnswerNum=4;
            switch (rightAnswer){
                case "A":
                    rigthAnswerNum=0;
                    break;
                case "B":
                    rigthAnswerNum=1;
                    break;
                case "C":
                    rigthAnswerNum=2;
                    break;
                case "D":
                    rigthAnswerNum=3;
                    break;
            }

            if (rigthAnswerNum==answerPosition){
                questionList.get(position).setRight(true);
                Log.i(TAG,"当前位置"+position+"答案"+answerPosition+"是否正确");
            }
        }
    }
}
