package xyz.egoistk21.iFantasy.game;

class GamePresenter implements GameContract.Presenter {

    private static final String TAG = GamePresenter.class.getName();

    private GameContract.Model mModel;
    private GameContract.View mView;

    GamePresenter(GameContract.View view) {
        attachMV(view);
    }

    @Override
    public void attachMV(GameContract.View view) {
        mModel = new GameModel();
        mView = view;
    }

    @Override
    public void detachMV() {
        mModel = null;
        mView = null;
    }
}
