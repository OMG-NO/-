package com.jredu.tk.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.jredu.tk.entity.Compalin;
import com.jredu.tk.openhelper.OpenHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by du on 2016/11/6.
 * 吐槽Dao
 */

public class ComplainDao {
    private Dao<Compalin, Integer> dao;

    public ComplainDao(Context mContext) {
        OpenHelper helper = OpenHelper.getIncentantce(mContext);
        try {
            dao = helper.getDao(Compalin.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //根据账号查找
    public List<Compalin> findComplainsByAccount(Compalin compalin){
        try {
            return dao.queryForEq("account",compalin.getAccount());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //添加一个吐槽
    public long addComplain(Compalin c) {
        long id = 0;
        try {
            id = dao.create(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
