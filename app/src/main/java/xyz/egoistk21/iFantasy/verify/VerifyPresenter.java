package xyz.egoistk21.iFantasy.verify;

import android.util.Log;

import com.trello.rxlifecycle2.components.RxActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.User;
import xyz.egoistk21.iFantasy.util.DBUtil;
import xyz.egoistk21.iFantasy.util.ToastUtil;

/**
 * Created by egoistk21 on 2018/3/22.
 */

class VerifyPresenter implements VerifyContract.Presenter {

    private static final String TAG = VerifyPresenter.class.getName();

    private VerifyContract.Model mModel;
    private VerifyContract.View mView;

    VerifyPresenter(VerifyContract.View view) {
        attachMV(view);
    }

    @Override
    public void attachMV(VerifyContract.View view) {
        mModel = new VerifyModel();
        mView = view;
    }

    @Override
    public void detachMV() {
        mModel = null;
        mView = null;
    }

    @Override
    public void login(String phone, String zone, String code, RxActivity rxActivity) {
        mModel.login(phone, zone, code, rxActivity, new Observer<HttpResult<User>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
                mView.showPB();
            }

            @Override
            public void onNext(HttpResult<User> userHttpResult) {
                Log.d(TAG, "onNext: " + userHttpResult.toString());
                if (201 == userHttpResult.getState()) {
                    DBUtil.setUser(userHttpResult.getResult());
                    mView.go2Register();
                } else if (200 == userHttpResult.getState()) {
                    DBUtil.setUser(userHttpResult.getResult());
                    mView.go2Login();
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
}
