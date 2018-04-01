package xyz.egoistk21.iFantasy.util;

import android.widget.Toast;

import xyz.egoistk21.iFantasy.IFantasyApplication;

/**
 * Created by egoistk21 on 2018/3/27.
 */

public class ToastUtil {

    private static Toast sToast = Toast.makeText(IFantasyApplication.getInstance(), null, Toast.LENGTH_LONG);

    private ToastUtil() {
    }

    public static void show(String content) {
        sToast.setText(content);
        sToast.show();
    }
}
