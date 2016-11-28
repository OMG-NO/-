package com.jredu.tk.entity;

/**
 * Created by 昂首天下 on 2016/11/4.
 */

public class Expandlable {
    private String title;
    private String section;
    private String sum;
    private int turn;

    public Expandlable() {
    }

    public Expandlable(String title, String section, String sum, int turn) {
        this.title = title;
        this.section = section;
        this.sum = sum;
        this.turn=turn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}
