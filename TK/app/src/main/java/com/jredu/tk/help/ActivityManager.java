package com.jredu.tk.help;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by du on 2016/11.9.
 * Activity的管理
 */
public class ActivityManager {
    private List<Activity> list = new LinkedList<Activity>();
    private static ActivityManager instance = null;

    private ActivityManager() {

    }

    public static ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    public void addActivity(Activity activity) {
        list.add(activity);
    }

    public void removeActivity(Activity activity) {
        list.remove(activity);
    }

    public void exit() {
        for (Activity activity : list) {
            if (!activity.isFinishing() && activity != null) {
                activity.finish();
            }
        }
        int id = android.os.Process.myPid();
        if (id != 0) {
            android.os.Process.killProcess(id);
        }
    }
}
