package xyz.egoistk21.iFantasy.main.recruit;

import android.util.Log;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.RawPlayer;
import xyz.egoistk21.iFantasy.bean.RecruitInfo;
import xyz.egoistk21.iFantasy.bean.RecruitResult;

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
        mView = view;
    }

    @Override
    public void detachMV() {
        mModel = null;
        mView = null;
    }

    @Override
    public void getRecruitInfo(int userId, LifecycleProvider rxLifecycle) {
        mModel.getRecruitInfo(userId, rxLifecycle, new Observer<HttpResult<RecruitInfo>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(HttpResult<RecruitInfo> recruitInfoHttpResult) {
                Log.d(TAG, "onNext: " + recruitInfoHttpResult.toString());
                if (0 == recruitInfoHttpResult.getState()) {
                    mView.setRecruitInfo(recruitInfoHttpResult.getResult());
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

    @Override
    public void getPlayers(int pos, int type, LifecycleProvider rxLifecycle) {
        mModel.getRawPlayers(pos, type, rxLifecycle, new Observer<HttpResult<ArrayList<RawPlayer>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(HttpResult<ArrayList<RawPlayer>> listHttpResult) {
                Log.d(TAG, "onNext: " + listHttpResult.toString());
                if (0 == listHttpResult.getState()) {
                    mView.setRawPlayers(listHttpResult.getResult());
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

    @Override
    public void luckyRecruit(int userId, LifecycleProvider rxLifecycle) {
        mModel.luckyRecruit(userId, rxLifecycle, new Observer<HttpResult<RecruitResult>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(HttpResult<RecruitResult> recruitResultHttpResult) {
                Log.d(TAG, "onNext: " + recruitResultHttpResult.toString());
                if (0 == recruitResultHttpResult.getState()) {
                    mView.showLuckyRecruitResult(recruitResultHttpResult.getResult());
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

    @Override
    public void pentaLuckyRecruit(int userId, LifecycleProvider rxLifecycle) {
        mModel.pentaLuckyRecruit(userId, rxLifecycle, new Observer<HttpResult<ArrayList<RecruitResult>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(HttpResult<ArrayList<RecruitResult>> arrayListHttpResult) {
                Log.d(TAG, "onNext: " + arrayListHttpResult.getState());
                if (0 == arrayListHttpResult.getState()) {
                    mView.showPentaLuckyRecruitResult(arrayListHttpResult.getResult());
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
