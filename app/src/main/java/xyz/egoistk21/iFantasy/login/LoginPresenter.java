package xyz.egoistk21.iFantasy.login;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by egoistk21 on 2018/3/22.
 */

class LoginPresenter implements LoginContract.Presenter {

    private static final String TAG = LoginActivity.class.getName();

    private LoginContract.Model mModel;
    private LoginContract.View mView;

    LoginPresenter(LoginContract.View view) {
        attachMV(view);
    }

    @Override
    public void attachMV(LoginContract.View view) {
        mModel = new LoginModel();
        mView = view;
    }

    @Override
    public void detachMV() {
        mModel = null;
        mView = null;
    }

    @Override
    public void getCode() {

    }

    @Override
    public void login(String phone, String zone, String code) {
        mModel.login(phone, zone, code, new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
            }
        });
    }
}
