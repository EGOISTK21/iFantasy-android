package xyz.egoistk21.iFantasy.main.home;

class HomePresenter implements HomeContract.Presenter {

    private static final String TAG = HomePresenter.class.getName();

    private HomeContract.Model mModel;
    private HomeContract.View mView;

    HomePresenter(HomeContract.View view) {
        attachMV(view);
    }

    @Override
    public void attachMV(HomeContract.View view) {
        mModel = new HomeModel();
        mView = view;
    }

    @Override
    public void detachMV() {
        mModel = null;
        mView = null;
    }
}
