package xyz.egoistk21.iFantasy.util;

import android.content.Context;
import android.content.SharedPreferences;

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

    public static void init(Context context) {
        sSharedPreferences = context.getSharedPreferences("iFantasy", Context.MODE_PRIVATE);
        sEditor = sSharedPreferences.edit();
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("iFantasyrealm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        sRealm = Realm.getDefaultInstance();
    }

    public static void close() {
        sRealm.close();
    }


}
