package xyz.egoistk21.iFantasy.welcome;

import android.util.Log;

import com.trello.rxlifecycle2.components.RxActivity;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.User;
import xyz.egoistk21.iFantasy.util.DBUtil;
import xyz.egoistk21.iFantasy.util.ToastUtil;

/**
 * Created by EGOISTK21 on 2018/3/22.
 */

class WelcomePresenter implements WelcomeContract.Presenter {

    private static final String TAG = WelcomePresenter.class.getName();

    private WelcomeContract.Model mModel;
    private WelcomeContract.View mView;

    WelcomePresenter(WelcomeContract.View view) {
        attachMV(view);
    }

    @Override
    public void attachMV(WelcomeContract.View view) {
        mModel = new WelcomeModel();
        mView = view;
    }

    @Override
    public void detachMV() {
        mModel = null;
        mView = null;
    }

    @Override
    public boolean isLogin() {
        return mModel.isLogin();
    }

    @Override
    public void register(String phone, String nickname, RxActivity rxActivity) {
        mModel.register(phone, nickname, rxActivity, new Observer<HttpResult<User>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
                mView.showPB();
            }

            @Override
            public void onNext(HttpResult<User> userHttpResult) {
                Log.d(TAG, "onNext: " + userHttpResult.toString());
                if (200 == userHttpResult.getState()) {
                    User user = userHttpResult.getResult();
                    DBUtil.setUser(user);
                    JMessageClient.register(user.getNickname(), "12345qwert", new BasicCallback() {
                        @Override
                        public void gotResult(int i, String s) {
                            Log.d(TAG, "gotResult: " + i + " " + s);
                        }
                    });
                    mView.startGame();
                } else {
                    mView.dismissPB();
                    ToastUtil.show(userHttpResult.getError());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
                mView.dismissPB();
                ToastUtil.show(e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
                mView.dismissPB();
            }
        });
    }

    @Override
    public void login(String phone, RxActivity rxActivity) {
        mModel.login(phone, rxActivity, new Observer<HttpResult<User>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
                mView.showPB();
            }

            @Override
            public void onNext(HttpResult<User> userHttpResult) {
                Log.d(TAG, "onNext: " + userHttpResult.toString());
                if (200 == userHttpResult.getState()) {
                    User user = userHttpResult.getResult();
                    DBUtil.setUser(user);
                    JMessageClient.login(user.getNickname(), "12345qwert", new BasicCallback() {
                        @Override
                        public void gotResult(int i, String s) {
                            Log.d(TAG, "gotResult: " + i + " " + s);
                        }
                    });
                    mView.startGame();
                } else {
                    DBUtil.setLoginToken(null);
                    mView.dismissPB();
                    ToastUtil.show(userHttpResult.getError());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
                mView.dismissPB();
                ToastUtil.show(e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
                mView.dismissPB();
            }
        });
    }
}
