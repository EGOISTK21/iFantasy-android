package xyz.egoistk21.iFantasy.welcome;

import android.util.Log;

import com.trello.rxlifecycle2.LifecycleProvider;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.User;
import xyz.egoistk21.iFantasy.util.DBUtil;
import xyz.egoistk21.iFantasy.util.StringUtil;
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
    public void register(final String phone, String nickname, final LifecycleProvider rxLifecycle) {
        mModel.register(phone, nickname, rxLifecycle, new Observer<HttpResult<User>>() {
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
                    JMessageClient.register(StringUtil.generateJGName(user.getId()), "12345qwert", new BasicCallback() {
                        @Override
                        public void gotResult(int i, String s) {
                            Log.d(TAG, "JMessageClient register: " + i + " " + s);
                            switch (i) {
                                case 0:
                                    ToastUtil.show("注册成功");
                                    login(phone, rxLifecycle);
                                    break;
                                case 898001:
                                    ToastUtil.show("用户名已存在");
                                    break;
                                case 871301:
                                    ToastUtil.show("密码格式错误");
                                    break;
                                case 871304:
                                    ToastUtil.show("密码错误");
                                    break;
                                default:
                                    ToastUtil.show(s);
                                    break;
                            }
                        }
                    });
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
    public void login(String phone, LifecycleProvider rxLifecycle) {
        mModel.login(phone, rxLifecycle, new Observer<HttpResult<User>>() {
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
                    JMessageClient.login("iFantasy_android_" + user.getId(), "12345qwert", new BasicCallback() {
                        @Override
                        public void gotResult(int i, String s) {
                            Log.d(TAG, "JMessageClient login: " + i + " " + s);
                            switch (i) {
                                case 801003:
                                    ToastUtil.show("用户名不存在");
                                    break;
                                case 871301:
                                    ToastUtil.show("密码格式错误");
                                    break;
                                case 801004:
                                    ToastUtil.show("密码错误");
                                    break;
                                case 0:
                                    ToastUtil.show("登陆成功");
                                    break;
                                default:
                                    break;
                            }
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
