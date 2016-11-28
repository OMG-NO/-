package com.jredu.tk.entity;

/**
 * Created by du on 2016/11/2.
 * 课程
 */
public class Course {
    //id
    private int id;
    //课程名称
    private String name;
    //课程描述
    private String desc;
    //课程代表图片
    private int imgUrl;

    public Course() {
    }

    public Course(int id, String name, String desc, int imgUrl) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.imgUrl = imgUrl;
    }

    public Course(int id, String name, int imgUrl) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }
}
