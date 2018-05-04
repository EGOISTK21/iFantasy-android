package xyz.egoistk21.iFantasy.main.chat;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

import io.reactivex.Observer;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.SimplePlayer;
import xyz.egoistk21.iFantasy.bean.User;

interface FriendContract {
    interface Model {
        void getUsers(String nickname, LifecycleProvider rxLifecycle, Observer<HttpResult<User>> observer);
    }

    interface View {
        void showPB();

        void dismissPB();
    }

    interface Presenter {
        void attachMV(View view);

        void detachMV();

        void getUsers(String nickname, LifecycleProvider rxLifecycle);
    }
}
