package xyz.egoistk21.iFantasy.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import xyz.egoistk21.iFantasy.bean.User;

/**
 * Created by EGOISTK21 on 2018/3/22.
 */

public class DBUtil {

    private static final String TAG = DBUtil.class.getName();

    private static SharedPreferences sSharedPreferences;
    private static Realm sRealm;
    private static User sUser;

    private DBUtil() {
    }

    public static boolean verifyPhone(String phone) {
        // "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(17[0,1,3,5,6,7,8])|(18[0-9])|(166))\\d{8}$";
        return !TextUtils.isEmpty(phone) && phone.matches(telRegex);
    }

    public static boolean verifyCode(String code) {
        return !TextUtils.isEmpty(code) && code.length() == 4;
    }

    public static void init(Context context) {
        sSharedPreferences = context.getSharedPreferences("iFantasy-android", Context.MODE_PRIVATE);
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("iFantasy-android")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        sRealm = Realm.getDefaultInstance();
    }

    public static void close() {
        sRealm.close();
    }

    public static void setLoginToken(String loginoken) {
        sRealm.beginTransaction();
        if (getUser() != null)
            sUser.setLogintoken(loginoken);
        sRealm.commitTransaction();
    }

    public static void setAccessToken(String accessToken) {
        sRealm.beginTransaction();
        if (getUser() != null)
            sUser.setAccesstoken(accessToken);
        sRealm.commitTransaction();
    }

    public static String getLoginToken() {
        return sUser == null ? null : sUser.getLogintoken();
    }

    public static String getAccessToken() {
        return sUser == null ? null : sUser.getAccesstoken();
    }

    public static void setUser(User user) {
        if (user != null) {
            sRealm.beginTransaction();
            if (getUser() != null && user.getId() != getUser().getId()) {
                getUser().deleteFromRealm();
            }
            sRealm.copyToRealmOrUpdate(user);
            sRealm.commitTransaction();
        }
    }

    public static User getUser() {
        if (sUser == null) {
            sUser = sRealm.where(User.class).findFirst();
        }
        return sUser;
    }

    public static int refreshMoney(int refresh) {
        sRealm.beginTransaction();
        sUser.refreshMoney(refresh);
        sRealm.commitTransaction();
        return sUser.getMoney();
    }

}
