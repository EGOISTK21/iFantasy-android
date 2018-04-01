package xyz.egoistk21.iFantasy;

import android.app.Application;

import com.mob.MobSDK;

import xyz.egoistk21.iFantasy.util.DBUtil;

/**
 * Created by EGOISTK21 on 2018/3/21.
 */

public class IFantasyApplication extends Application {

    private static IFantasyApplication sInstance;

    public static IFantasyApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        DBUtil.init(this);
        MobSDK.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DBUtil.close();
    }
}
