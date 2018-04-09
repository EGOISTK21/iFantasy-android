package xyz.egoistk21.iFantasy.main;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import io.reactivex.Observer;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.User;

/**
 * Created by EGOISTK21 on 2018/3/22.
 */

interface MainContract {
    interface Model {
        boolean isLogin();

        boolean isOnline();

        void register(String phone, String nickname, RxAppCompatActivity rxAppCompatActivity, Observer<HttpResult<User>> observer);

        void login(String phone, RxAppCompatActivity rxAppCompatActivity, Observer<HttpResult<User>> observer);
    }

    interface View {
        void startGame();

        void showPB();

        void dismissPB();
    }

    interface Presenter {
        void attachMV(View view);

        void detachMV();

        boolean isLogin();

        void register(String phone, String nickname, RxAppCompatActivity rxAppCompatActivity);

        void login(String phone, RxAppCompatActivity rxAppCompatActivity);
    }
}
