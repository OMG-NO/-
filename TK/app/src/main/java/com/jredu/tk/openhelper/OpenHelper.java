package com.jredu.tk.openhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.jredu.tk.entity.Compalin;
import com.jredu.tk.entity.User;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016/11/2.
 * 数据库帮助类
 */
public class OpenHelper extends OrmLiteSqliteOpenHelper {
    private static final String DB_NAME = "jereh.db";
    private static final int DB_VERSION = 1;

    public OpenHelper(Context mContext) {
        super(mContext, DB_NAME, null, DB_VERSION);
    }

    private static OpenHelper helper;

    public static OpenHelper getIncentantce(Context mContext) {
        if (helper == null) {
            helper = new OpenHelper(mContext);
        }
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Compalin.class);
            TableUtils.createTableIfNotExists(connectionSource, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource,Compalin.class,true);
            TableUtils.dropTable(connectionSource,User.class,true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
