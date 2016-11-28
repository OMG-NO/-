package com.jredu.tk.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.jredu.tk.entity.User;
import com.jredu.tk.openhelper.OpenHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by du on 2016/11/8.
 * 用户操作Dao
 */
public class UserDao {
    private Dao<User, String> dao;

    public UserDao(Context mContext) {
        OpenHelper helper = OpenHelper.getIncentantce(mContext);
        try {
            dao = helper.getDao(User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //查询全部
    public List<User> findUser(){
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //根据账号查找用户
    public User findUserByAccount(String account){
        try {
            return dao.queryForEq("account",account).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
         return null;
    }
    //添加一个用户
    public long addUser(User c) {
        long id = 0;
        try {
            id = dao.create(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    //根据账号修改用户信息
    public int updateUser(User user){
        int flag=0;
        try {
            dao.delete(user);
            dao.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
