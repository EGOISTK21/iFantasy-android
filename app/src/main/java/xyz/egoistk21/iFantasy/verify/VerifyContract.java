package xyz.egoistk21.iFantasy.verify;

import com.trello.rxlifecycle2.components.RxActivity;

import io.reactivex.Observer;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.User;

/**
 * Created by egoistk21 on 2018/3/22.
 */

interface VerifyContract {
    interface Model {
        void login(String phone, String zone, String code, RxActivity rxActivity, Observer<HttpResult<User>> observer);
    }

    interface View {
        void go2CodeView();

        void back2PhoneView();

        void showPB();

        void dismissPB();

        void go2Register();

        void go2Login();
    }

    interface Presenter {
        void attachMV(View view);

        void detachMV();

        void login(String phone, String zone, String code, RxActivity rxActivity);
    }
}
