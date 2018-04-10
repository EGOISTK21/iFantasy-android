package xyz.egoistk21.iFantasy.main;

import android.util.Log;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

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

class MainPresenter implements MainContract.Presenter {

    private static final String TAG = MainPresenter.class.getName();

    private MainContract.Model mModel;
    private MainContract.View mView;

    MainPresenter(MainContract.View view) {
        attachMV(view);
    }

    @Override
    public void attachMV(MainContract.View view) {
        mModel = new MainModel();
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
    public void register(String phone, String nickname, RxAppCompatActivity rxAppCompatActivity) {
        mModel.register(phone, nickname, rxAppCompatActivity, new Observer<HttpResult<User>>() {
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
    public void login(String phone, RxAppCompatActivity rxAppCompatActivity) {
        mModel.login(phone, rxAppCompatActivity, new Observer<HttpResult<User>>() {
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
