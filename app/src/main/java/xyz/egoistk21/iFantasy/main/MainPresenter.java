package xyz.egoistk21.iFantasy.main;

/**
 * Created by EGOISTK21 on 2018/3/22.
 */

class MainPresenter implements MainContract.Presenter {

    private MainContract.Model mModel;
    private MainContract.View mView;

    MainPresenter(MainContract.View view) {
        attachMV(view);
    }

    @Override
    public void attachMV(MainContract.View view) {
        mModel = new MainModel();
        mView = view;
    }

    @Override
    public void detachMV() {
        mModel = null;
        mView = null;
    }

    @Override
    public boolean isLogined() {
        return mModel.isLogined() && mModel.isOnline();
    }
}
