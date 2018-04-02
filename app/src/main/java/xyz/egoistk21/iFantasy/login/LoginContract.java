package xyz.egoistk21.iFantasy.login;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import io.reactivex.Observer;
import xyz.egoistk21.iFantasy.bean.HttpResult;

/**
 * Created by egoistk21 on 2018/3/22.
 */

interface LoginContract {
    interface Model {
        void login(String phone, String zone, String code, RxAppCompatActivity rxAppCompatActivity, Observer<HttpResult> observer);
    }

    interface View {
        void go2VerificationCodeView();

        void back2PhoneView();

        void showPB();

        void dismissPB();

        void loginSuccess();
    }

    interface Presenter {
        void attachMV(View view);

        void detachMV();

        void login(String phone, String zone, String code, RxAppCompatActivity rxAppCompatActivity);
    }
}
