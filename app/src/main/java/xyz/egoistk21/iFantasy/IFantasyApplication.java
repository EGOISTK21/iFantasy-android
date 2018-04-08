package xyz.egoistk21.iFantasy;

import android.app.Application;
import android.util.Log;

import com.mob.MobSDK;

import xyz.egoistk21.iFantasy.util.DBUtil;

/**
 * Created by EGOISTK21 on 2018/3/21.
 */

public class IFantasyApplication extends Application {

    private static final String TAG = IFantasyApplication.class.getName();

    private static IFantasyApplication sInstance;

    public static IFantasyApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();
        sInstance = this;
        DBUtil.init(this);
        MobSDK.init(this);
    }

    @Override
    public void onTerminate() {
        Log.d(TAG, "onTerminate");
        super.onTerminate();
        DBUtil.close();
    }
}
