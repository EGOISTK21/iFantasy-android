package xyz.egoistk21.iFantasy.main.gallery;

import android.util.Log;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.SimplePlayer;

public class GalleryPresenter implements GalleryContract.Presenter {

    private static final String TAG = GalleryPresenter.class.getName();

    private GalleryContract.Model mModel;
    private GalleryContract.View[] mViews;

    private GalleryPresenter() {
        mViews = new GalleryContract.View[6];
    }

    public static GalleryPresenter getInstance(int pos, GalleryContract.View view) {
        SingletonHolder.INSTANCE.attachMV(pos, view);
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void attachMV(int pos, GalleryContract.View view) {
        mModel = new GalleryModel();
        mViews[pos] = view;
        Log.d(TAG, "attachMV: " + pos);
    }

    @Override
    public void detachMV(int pos) {
//        mModel = null;
        mViews[pos] = null;
    }

    @Override
    public void getSimplePlayers(int userId, final int pos, final int order, LifecycleProvider rxLifecycle) {
        if (GalleryFragment.mIsRecruit) {
//            if (DBUtil.isSimplePlayerNull()) {
            mModel.getRecruitSimplePlayers(userId, 0, -1, rxLifecycle, new Observer<HttpResult<List<SimplePlayer>>>() {
                @Override
                public void onSubscribe(Disposable d) {
                    Log.d(TAG, "onSubscribe");
                }

                @Override
                public void onNext(HttpResult<List<SimplePlayer>> listHttpResult) {
                    Log.d(TAG, "onNext: " + listHttpResult.toString());
                    if (0 == listHttpResult.getState()) {
                        mViews[pos].setSimplePlayers(listHttpResult.getResult());
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
//            } else {
//                mViews[pos].setSimplePlayers(DBUtil.getSimplePlayers(pos, order));
//            }
        } else {
            mModel.getTeamSimplePlayers(userId, pos, order, rxLifecycle, new Observer<HttpResult<List<SimplePlayer>>>() {
                @Override
                public void onSubscribe(Disposable d) {
                    Log.d(TAG, "onSubscribe");
                }

                @Override
                public void onNext(HttpResult<List<SimplePlayer>> listHttpResult) {
                    Log.d(TAG, "onNext: " + listHttpResult.toString());
                    if (0 == listHttpResult.getState()) {
                        mViews[pos].setSimplePlayers(listHttpResult.getResult());
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

    private static class SingletonHolder {
        private static final GalleryPresenter INSTANCE = new GalleryPresenter();
    }
}
