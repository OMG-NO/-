package com.jredu.tk.control;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jredu.tk.datamanager.RequestManager;
import com.jredu.tk.entity.Option;
import com.jredu.tk.entity.Question;
import com.jredu.tk.entity.QuestionJson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by HunBing on 2016/11/8.
 */

public class RequestQuestion {
    Context context;
    Gson gson;

    OnRequestSuccessListener requestSuccessListener;

    public void setRequestSuccessListener(OnRequestSuccessListener requestSuccessListener){
        this.requestSuccessListener=requestSuccessListener;
    }

    /**
     * 添加参数列表并且请求,同时解析字符串
     * @param context
     */
    public void getRequestQuestion(Context context, Object requestBundle,Class<?> bean,String url) {
        gson = new Gson();
        HashMap<String, String> params = new HashMap<>();
        try {
            params=GetParameterValue.getParamValue(bean,requestBundle);
            showParam(params);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        final List<QuestionJson> questionJsonList = new ArrayList<QuestionJson>();
        RequestManager.getInstance(context).requestAsyn(
                "subjects/"+url,
                RequestManager.TYPE_POST_JSON,
                params,
                new RequestManager.ReqCallBack<String>() {
                    @Override
                    public void onReqSuccess(String result) {
                        JsonParser parser = new JsonParser();
                        JsonElement element = parser.parse(result);
                        JsonArray jsonArray = null;
                        if (element.isJsonArray()) {
                            jsonArray = element.getAsJsonArray();
                        }
                        Iterator it = jsonArray.iterator();
                        while (it.hasNext()) {
                            JsonElement e = (JsonElement) it.next();
                            //JsonElement转换为JavaBean对象
                            QuestionJson questionJson = gson.fromJson(e, QuestionJson.class);
                            questionJsonList.add(questionJson);
                        }
                        setQuestionList(questionJsonList);
                    }

                    @Override
                    public void onReqFailed(String errorMsg) {

                    }
                }
        );
    }

    public void showParam(HashMap<String,String> param){
        for (String string : param.keySet()) {
            Log.i("参数",string+"   "+param.get(string));
        }
    }

    /**
     * 将请求到的数据进行解析之后 转换成{@code Question},并在之后进行处理
     */
    private void setQuestionList(List<QuestionJson> questionJsons){
        List<Question> questionList=new ArrayList<>();
        for (int i=0;i<questionJsons.size();i++){
            QuestionJson questionJson=questionJsons.get(i);
            Question question=new Question(
                    questionJson.getId(),
                    questionJson.getTitle(),
                    questionJson.getAnswers().getAnalysis(),
                    questionJson.getAnswers().getAnswer()
            );
            if (OptionBean2OptionList(questionJson.getOptions())!=null) {
                question.setOptionList(OptionBean2OptionList(questionJson.getOptions()));
            }

            questionList.add(question);
        }

        requestSuccessListener.getReponseQuestion(questionList);
    }

    /**
     * 将{@code OptionBean}的数据进行梳理  这里的数据不确定
     */
    private List<Option> OptionBean2OptionList(List<QuestionJson.OptionsBean> optionsBeanList){
        Log.i("下标越界  RequestQuestion",optionsBeanList.size()+"");
        QuestionJson.OptionsBean optionsBean=null;
        try {
            optionsBean = optionsBeanList.get(0);
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        if (optionsBean!=null) {
            Option optionA = new Option(optionsBean.getOptionA());
            Option optionB = new Option(optionsBean.getOptionB());
            Option optionC = new Option(optionsBean.getOptionC());
            Option optionD = new Option(optionsBean.getOptionD());
            List<Option> optionList = new ArrayList<>();
            optionList.add(optionA);
            optionList.add(optionB);
            optionList.add(optionC);
            optionList.add(optionD);

            return optionList;
        }
        return null;
    }



    public interface OnRequestSuccessListener{
        public void getReponseQuestion(List<Question> questionList);
    }
}
