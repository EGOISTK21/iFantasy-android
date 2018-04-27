package xyz.egoistk21.iFantasy.main.settings;

import android.util.Log;

import com.trello.rxlifecycle2.LifecycleProvider;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.User;

class SettingsPresenter implements SettingsContract.Presenter {

    private static final String TAG = SettingsPresenter.class.getName();

    private SettingsContract.Model mModel;
    private SettingsContract.View mView;

    public SettingsPresenter(SettingsContract.View view) {
        attachMV(view);
    }

    @Override
    public void attachMV(SettingsContract.View view) {
        mModel = new SettingsModel();
        mView = view;
    }

    @Override
    public void detachMV() {
        mModel = null;
        mView = null;
    }

    @Override
    public void logout(int userId, LifecycleProvider rxLifecycle) {
        mModel.logout(userId, rxLifecycle, new Observer<HttpResult<User>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(HttpResult<User> userHttpResult) {
                Log.d(TAG, "onNext: " + userHttpResult);
                if (200 == userHttpResult.getState()) {
                    mView.onLogout();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        });
    }
}
