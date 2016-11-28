package com.jredu.tk.entity;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by du on 2016/11/7.
 * 用户
 */
@DatabaseTable(tableName = "user")
public class User implements Parcelable {
    //昵称
    @DatabaseField
    private String name;
    //头像
    @DatabaseField
    private String photo;
    //题库
    @DatabaseField
    private String questionBank;
    //高考地方
    @DatabaseField
    private String place;
    //考生类型
    @DatabaseField
    private String type;
    //性别
    @DatabaseField
    private String sex;
    //高考年份
    @DatabaseField
    private String year;
    //帐号
    @DatabaseField(id = true ,columnName = "account")
    private String account;
    //头像是下载的还是从本地修改的
    @DatabaseField
    private boolean changePhoto=false;
    //是否登录
    @DatabaseField
    private boolean login=false;
    public User() {
    }

    public User(String name, String photo, String questionBank, String place, String type, String sex, String year) {
        this.name = name;
        this.photo = photo;
        this.questionBank = questionBank;
        this.place = place;
        this.type = type;
        this.sex = sex;
        this.year = year;
    }

    public User(String name, String photo, String questionBank, String place, String type, String sex, String year, String account) {
        this.name = name;
        this.photo = photo;
        this.questionBank = questionBank;
        this.place = place;
        this.type = type;
        this.sex = sex;
        this.year = year;
        this.account = account;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public boolean isChangePhoto() {
        return changePhoto;
    }

    public void setChangePhoto(boolean changePhoto) {
        this.changePhoto = changePhoto;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getQuestionBank() {
        return questionBank;
    }

    public void setQuestionBank(String questionBank) {
        this.questionBank = questionBank;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            bundle.putString("photo", photo);
            bundle.putString("questionBank", questionBank);
            bundle.putString("place", place);
            bundle.putString("type", type);
            bundle.putString("sex", sex);
            bundle.putString("year", year);
            bundle.putString("account", account);
            bundle.putBoolean("changePhoto",changePhoto);
            bundle.putBoolean("login",login);
            parcel.writeBundle(bundle);
    }

    // 用来创建自定义的Parcelable的对象
    public static final Creator<User> CREATOR = new Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private User(Parcel in) {
        Bundle bundle = in.readBundle();
        name = bundle.getString("name");
        photo = bundle.getString("photo");
        questionBank = bundle.getString("questionBank");
        place = bundle.getString("place");
        type = bundle.getString("type");
        sex = bundle.getString("sex");
        year = bundle.getString("year");
        account = bundle.getString("account");
        changePhoto=bundle.getBoolean("changePhoto");
        login=bundle.getBoolean("login");
    }
}
