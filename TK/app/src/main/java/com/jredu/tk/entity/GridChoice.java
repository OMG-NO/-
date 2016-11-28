package com.jredu.tk.entity;

/**
 * Created by 昂首天下 on 2016/11/2.
 */


/**
 * 用来描述试题选择的对象
 */
public class GridChoice {
    private int pic;
    private String text;

    public GridChoice() {
    }

    public GridChoice(int pic, String text) {
        this.pic = pic;
        this.text = text;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
