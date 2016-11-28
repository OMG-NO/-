package com.jredu.tk.entity;

/**
 * Created by 昂首天下 on 2016/11/2.
 */

public class SeniorHighExam {
    private int oldExam;
    private String name;
    private int turn;

    public SeniorHighExam() {
    }

    public SeniorHighExam(String name) {
        this.name = name;
    }

    public SeniorHighExam(int oldExam, String name, int turn) {
        this.oldExam = oldExam;
        this.name = name;
        this.turn = turn;
    }

    public int getOldExam() {
        return oldExam;
    }

    public void setOldExam(int oldExam) {
        this.oldExam = oldExam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}
