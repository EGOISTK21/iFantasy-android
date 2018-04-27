package xyz.egoistk21.iFantasy.main.settings;

import com.trello.rxlifecycle2.LifecycleProvider;

import io.reactivex.Observer;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.User;

interface SettingsContract {
    interface Model {
        void logout(int userId, LifecycleProvider rxLifecycle, Observer<HttpResult<User>> observer);
    }

    interface View {
        void onLogout();

        void showPB();

        void dismissPB();
    }

    interface Presenter {
        void attachMV(View view);

        void detachMV();

        void logout(int userId, LifecycleProvider rxLifecycle);
    }
}
