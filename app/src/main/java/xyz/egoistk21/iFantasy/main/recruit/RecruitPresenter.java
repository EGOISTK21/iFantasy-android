package xyz.egoistk21.iFantasy.main.recruit;

import android.util.Log;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.RawPlayer;
import xyz.egoistk21.iFantasy.bean.RecruitInfo;

class RecruitPresenter implements RecruitContract.Presenter {

    private static final String TAG = RecruitPresenter.class.getName();

    private RecruitContract.Model mModel;
    private RecruitContract.View mView;

    RecruitPresenter(RecruitContract.View view) {
        attachMV(view);
    }

    @Override
    public void attachMV(RecruitContract.View view) {
        mModel = new RecruitModel();
        mView = mView;
    }

    @Override
    public void detachMV() {
        mModel = null;
        mView = null;
    }

    @Override
    public void getRecruitInfo(int user_id, LifecycleProvider rxLifecycle) {
        mModel.getRecruitInfo(user_id, rxLifecycle, new Observer<HttpResult<RecruitInfo>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(HttpResult<RecruitInfo> recruitInfoHttpResult) {
                Log.d(TAG, "onNext: " + recruitInfoHttpResult.toString());
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

    @Override
    public void getPlayers(int pos, int type, LifecycleProvider rxLifecycle) {
        mModel.getPlayers(pos, type, rxLifecycle, new Observer<HttpResult<ArrayList<RawPlayer>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(HttpResult<ArrayList<RawPlayer>> listHttpResult) {
                Log.d(TAG, "onNext: " + listHttpResult.toString());
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
