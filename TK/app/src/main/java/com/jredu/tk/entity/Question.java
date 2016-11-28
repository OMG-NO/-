package com.jredu.tk.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by HunBing on 2016/11/5.
 */

public class Question implements Parcelable {
    private int id;
    private String title;
    private List<Option> optionList;
    private String analysis;
    private String answer;

    private boolean isRight;
    private boolean isSelected;

    public Question(int id,String title, String analysis, String answer) {
        this.id=id;
        this.title = title;
        this.analysis = analysis;
        this.answer = answer;
    }

    public Question() {
    }


    protected Question(Parcel in) {
        id=in.readInt();
        title = in.readString();
        analysis = in.readString();
        answer = in.readString();
        isRight = in.readByte() != 0;
        isSelected = in.readByte() != 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

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

    public List<Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<Option> optionList) {
        this.optionList = optionList;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(analysis);
        dest.writeString(answer);
        dest.writeByte((byte) (isRight ? 1 : 0));
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }
}
