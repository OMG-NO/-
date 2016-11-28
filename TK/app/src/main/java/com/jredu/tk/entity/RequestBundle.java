package com.jredu.tk.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HunBing on 2016/11/9.
 *
 */

public class RequestBundle implements Parcelable {
    private String type;           //题型种类
    private String course;      //科目
    private String section;     //章
    private String topical;     //节
    private String degree;         //难度
    private String count;          //题目数量
    private String title;        //关键字
    private String questionId;      //题目id

    private String requestURL;      //请求地址

    public RequestBundle() {
    }

    public RequestBundle(String type, String title) {
        this.type = type;
        this.title = title;
    }

    public RequestBundle(String type,String course,String count){
        this.type=type;
        this.course=course;
        this.count=count;
    }

    protected RequestBundle(Parcel in) {
        type = in.readString();
        course = in.readString();
        section = in.readString();
        topical = in.readString();
        degree = in.readString();
        count = in.readString();
        title = in.readString();
        questionId=in.readString();

        requestURL = in.readString();
    }

    public static final Creator<RequestBundle> CREATOR = new Creator<RequestBundle>() {
        @Override
        public RequestBundle createFromParcel(Parcel in) {
            return new RequestBundle(in);
        }

        @Override
        public RequestBundle[] newArray(int size) {
            return new RequestBundle[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(course);
        dest.writeString(section);
        dest.writeString(topical);
        dest.writeString(degree);
        dest.writeString(count);
        dest.writeString(title);
        dest.writeString(questionId);
        dest.writeString(requestURL);
    }
}
