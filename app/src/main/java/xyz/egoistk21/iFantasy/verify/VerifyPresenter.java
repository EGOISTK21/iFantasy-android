package xyz.egoistk21.iFantasy.verify;

import android.util.Log;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.util.ToastUtil;

/**
 * Created by egoistk21 on 2018/3/22.
 */

class VerifyPresenter implements VerifyContract.Presenter {

    private static final String TAG = VerifyActivity.class.getName();

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
    public void login(String phone, String zone, String code, RxAppCompatActivity rxAppCompatActivity) {
        mModel.login(phone, zone, code, rxAppCompatActivity, new Observer<HttpResult>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe: ");
                mView.showPB();
            }

            @Override
            public void onNext(HttpResult httpResult) {
                Log.i(TAG, "onNext: " + httpResult.toString());
                if (201 == httpResult.getState()) {
                    mView.register();
                } else if (200 == httpResult.getState()) {
                    mView.login();
                } else {
                    ToastUtil.show(httpResult.getError());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: " + e.getMessage());
                ToastUtil.show(e.getMessage());
                mView.dismissPB();
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
                mView.dismissPB();
            }
        });
    }
}
