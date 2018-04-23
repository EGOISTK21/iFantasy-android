package xyz.egoistk21.iFantasy.main.gallery;

import android.util.Log;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.RawPlayer;
import xyz.egoistk21.iFantasy.util.DBUtil;

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
    public void getRawPlayers(final int pos, final int type, LifecycleProvider rxLifecycle) {
        if (DBUtil.isRawPlayersNull()) {
            mModel.getRawPlayers(0, 0, rxLifecycle, new Observer<HttpResult<ArrayList<RawPlayer>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(HttpResult<ArrayList<RawPlayer>> listHttpResult) {
                Log.d(TAG, "onNext: " + listHttpResult.toString());
                if (0 == listHttpResult.getState()) {
                    DBUtil.setRawPlayers(listHttpResult.getResult());
                    mViews[pos].setRawPlayers(DBUtil.getRawPlayers(pos, type));
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
        } else {
            mViews[pos].setRawPlayers(DBUtil.getRawPlayers(pos, type));
        }
    }

    private static class SingletonHolder {
        private static final GalleryPresenter INSTANCE = new GalleryPresenter();
    }
}
