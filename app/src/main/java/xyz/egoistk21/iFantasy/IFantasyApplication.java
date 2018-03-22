package xyz.egoistk21.iFantasy;

import android.app.Application;

import com.mob.MobSDK;

import xyz.egoistk21.iFantasy.util.DBUtil;

/**
 * Created by EGOISTK21 on 2018/3/21.
 */

public class IFantasyApplication extends Application {

    public static final String MOB_APPKEY = "24be6ffdbdc18";

    public static final String MOB_APPSECRET = "70935d0cbb5fa524403accc95ec55b84";

    @Override
    public void onCreate() {
        super.onCreate();
        DBUtil.init(this);
        MobSDK.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DBUtil.close();
    }
}
