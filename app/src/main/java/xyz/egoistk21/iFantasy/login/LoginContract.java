package xyz.egoistk21.iFantasy.login;

/**
 * Created by egoistk21 on 2018/3/22.
 */

interface LoginContract {
    interface Model {
        void login(String phone, String code);
    }

    interface View {
    }

    interface Presenter {
        void attachMV(View view);

        void detachMV();

        void login(String phone, String code);
    }
}
