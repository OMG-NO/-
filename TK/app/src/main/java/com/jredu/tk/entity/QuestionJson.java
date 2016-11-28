package com.jredu.tk.entity;

import java.util.List;

/**
 * Created by HunBing on 2016/11/7.
 */

public class QuestionJson {


    /**
     * id : 0
     * title : 下列各组词语中加点字的读音，全都正确的一组是
     * imgurl : null
     * degree : 1
     * answers : {"id":1,"answer":"2","analysis":"（A熨yù帖　拜谒yè　　B隽(juàn)永　\u201c绮\u201d读qǐ　　D发酵jiào）"}
     * course : 语文
     * section : 语言文字运用
     * topical : 字音
     * options : [{"id":1,"optionA":"熨(yùn)帖\t慰藉(jiè)\t遏(è)制\t拜谒(jié)","optionB":"遒(qiú)劲    隽(jùn)永    \t绮(qí)丽     \t横亘\r\r\n(gèn)","optionC":"绽(zhàn)开   邂(xiè)逅    \t佛龛(kān)    \t迥(jiǒng)异","optionD":"发酵(xiào)   静谧(mì)     \t\r\r\n恪(kè)守     \t雾霭(ái)","sid":0},{"id":2,"optionA":"矫揉造作　　\t白璧无瑕　　\t目不暇接　　\t全神贯注","optionB":"好高鹜远　　\t声名狼藉　　\t恼羞\r\r\n成怒　　\t明珠暗投","optionC":"虚无缥缈　　\t趋之若骛　　\t风烛残年　　\t仓皇失措","optionD":"扑塑迷离\r\r\n　　\t淋漓尽致　　\t不绝如缕　　\t谈笑风声","sid":0}]
     */

    private int id;
    private String title;
    private Object imgurl;
    private int degree;
    /**
     * id : 1
     * answer : 2
     * analysis : （A熨yù帖　拜谒yè　　B隽(juàn)永　“绮”读qǐ　　D发酵jiào）
     */

    private AnswersBean answers;
    private String course;
    private String section;
    private String topical;
    /**
     * id : 1
     * optionA : 熨(yùn)帖	慰藉(jiè)	遏(è)制	拜谒(jié)
     * optionB : 遒(qiú)劲    隽(jùn)永    	绮(qí)丽     	横亘
     (gèn)
     * optionC : 绽(zhàn)开   邂(xiè)逅    	佛龛(kān)    	迥(jiǒng)异
     * optionD : 发酵(xiào)   静谧(mì)
     恪(kè)守     	雾霭(ái)
     * sid : 0
     */

    private List<OptionsBean> options;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getImgurl() {
        return imgurl;
    }

    public void setImgurl(Object imgurl) {
        this.imgurl = imgurl;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public AnswersBean getAnswers() {
        return answers;
    }

    public void setAnswers(AnswersBean answers) {
        this.answers = answers;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getTopical() {
        return topical;
    }

    public void setTopical(String topical) {
        this.topical = topical;
    }

    public List<OptionsBean> getOptions() {
        return options;
    }

    public void setOptions(List<OptionsBean> options) {
        this.options = options;
    }

    public static class AnswersBean {
        private int id;
        private String answer;
        private String analysis;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getAnalysis() {
            return analysis;
        }

        public void setAnalysis(String analysis) {
            this.analysis = analysis;
        }
    }

    public static class OptionsBean {
        private int id;
        private String optionA;
        private String optionB;
        private String optionC;
        private String optionD;
        private int sid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOptionA() {
            return optionA;
        }

        public void setOptionA(String optionA) {
            this.optionA = optionA;
        }

        public String getOptionB() {
            return optionB;
        }

        public void setOptionB(String optionB) {
            this.optionB = optionB;
        }

        public String getOptionC() {
            return optionC;
        }

        public void setOptionC(String optionC) {
            this.optionC = optionC;
        }

        public String getOptionD() {
            return optionD;
        }

        public void setOptionD(String optionD) {
            this.optionD = optionD;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }
    }
}