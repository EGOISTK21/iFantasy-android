package xyz.egoistk21.iFantasy.login;

import io.reactivex.Observer;

/**
 * Created by egoistk21 on 2018/3/22.
 */

interface LoginContract {
    interface Model {
        void getCode();

        void login(String phone, String zone, String code, Observer<String> observer);
    }

    interface View {
        void go2VerificationCodeView();

        void back2PhoneView();
    }

    interface Presenter {
        void attachMV(View view);

        void detachMV();

        void getCode();

        void login(String phone, String zone, String code);
    }
}
