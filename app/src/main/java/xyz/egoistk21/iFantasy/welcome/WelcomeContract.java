package xyz.egoistk21.iFantasy.welcome;

import com.trello.rxlifecycle2.LifecycleProvider;

import io.reactivex.Observer;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.User;

/**
 * Created by EGOISTK21 on 2018/3/22.
 */

interface WelcomeContract {
    interface Model {
        boolean isLogin();

        boolean isOnline();

        void register(String phone, String nickname, LifecycleProvider rxLifecycle, Observer<HttpResult<User>> observer);

        void login(String phone, LifecycleProvider rxLifecycle, Observer<HttpResult<User>> observer);
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

        void register(String phone, String nickname, LifecycleProvider rxLifecycle);

        void login(String phone, LifecycleProvider rxLifecycle);
    }
}
