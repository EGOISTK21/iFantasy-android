package xyz.egoistk21.iFantasy.login;

/**
 * Created by egoistk21 on 2018/3/22.
 */

interface LoginContract {
    interface Model {
        void getCode();

        void login(String phone, String zone, String code, io.reactivex.Observer observer);
    }

    interface View {
    }

    interface Presenter {
        void attachMV(View view);

        void detachMV();

        void getCode();

        void login(String phone, String zone, String code);
    }
}
