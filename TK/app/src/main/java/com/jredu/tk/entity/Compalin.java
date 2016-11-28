package com.jredu.tk.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by du on 2016/11/5.
 */
@DatabaseTable(tableName = "complain")
public class Compalin {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String text;
    @DatabaseField
    private String date;
    @DatabaseField(columnName = "account")
    private String account;

    public Compalin() {
    }

    public Compalin(String text, String date) {
        this.text = text;
        this.date = date;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
