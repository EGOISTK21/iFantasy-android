package xyz.egoistk21.iFantasy;

import android.app.Application;
import android.util.Log;

import com.mob.MobSDK;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;
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
        JMessageClient.setDebugMode(true);
        JMessageClient.init(this);
//        JMessageClient.init(this, true);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    @Override
    public void onTerminate() {
        Log.d(TAG, "onTerminate");
        super.onTerminate();
        JMessageClient.logout();
        DBUtil.close();
    }
}
