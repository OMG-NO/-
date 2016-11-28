package com.jredu.tk.entity;

/**
 * Created by HunBing on 2016/11/2.
 */

public class RecommendType {
    private int id;
    private String name;

    public RecommendType(int id, String name) {
        this.id = id;
        this.name = name;
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
}
