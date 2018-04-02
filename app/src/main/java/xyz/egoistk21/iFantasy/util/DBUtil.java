package xyz.egoistk21.iFantasy.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by EGOISTK21 on 2018/3/22.
 */

public class DBUtil {
    private static SharedPreferences sSharedPreferences;
    private static SharedPreferences.Editor sEditor;
    private static Realm sRealm;

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
        sSharedPreferences = context.getSharedPreferences("iFantasy", Context.MODE_PRIVATE);
        sEditor = sSharedPreferences.edit();
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("iFantasy-Realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        sRealm = Realm.getDefaultInstance();
    }

    public static void close() {
        sRealm.close();
    }

}
