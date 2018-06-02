package xyz.egoistk21.iFantasy.main.bag;

import android.util.Log;

public class BagGalleryPresenter implements BagGalleryContract.Presenter {

    private static final String TAG = BagGalleryPresenter.class.getName();

    private BagGalleryContract.Model mModel;
    private BagGalleryContract.View[] mViews;

    private BagGalleryPresenter() {
        mViews = new BagGalleryContract.View[6];
    }

    public static BagGalleryPresenter getInstance(int pos, BagGalleryContract.View view) {
        SingletonHolder.INSTANCE.attachMV(pos, view);
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void attachMV(int pos, BagGalleryContract.View view) {
        mModel = new BagGalleryModel();
        mViews[pos] = view;
        Log.d(TAG, "attachMV: " + pos);
    }

    @Override
    public void detachMV(int pos) {
//        mModel = null;
        mViews[pos] = null;
    }


    private static class SingletonHolder {
        private static final BagGalleryPresenter INSTANCE = new BagGalleryPresenter();
    }
}
