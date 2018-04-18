package xyz.egoistk21.iFantasy.main.gallery;

import android.util.Log;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.RawPlayer;

public class GalleryPresenter implements GalleryContract.Presenter {

    private static final String TAG = GalleryContract.class.getName();

    private GalleryContract.Model mModel;
    private GalleryContract.View mView;

    GalleryPresenter(GalleryContract.View view) {
        attachMV(view);
    }

    @Override
    public void attachMV(GalleryContract.View view) {
        mModel = new GalleryModel();
        mView = view;
    }

    @Override
    public void detachMV() {
        mModel = null;
        mView = null;
    }

    @Override
    public void getRawPlayers(int pos, int type, LifecycleProvider rxLifecycle) {
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
}
