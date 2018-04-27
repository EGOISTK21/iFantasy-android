package xyz.egoistk21.iFantasy.main.player;

import android.util.Log;

import com.trello.rxlifecycle2.LifecycleProvider;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import xyz.egoistk21.iFantasy.bean.DetailPlayer;
import xyz.egoistk21.iFantasy.bean.HttpResult;

class PlayerPresenter implements PlayerContract.Presenter {

    private static final String TAG = PlayerPresenter.class.getName();

    private PlayerContract.Model mModel;
    private PlayerContract.View mView;

    PlayerPresenter(PlayerContract.View view) {
        attachMV(view);
    }

    @Override
    public void attachMV(PlayerContract.View view) {
        mView = view;
        mModel = new PlayerModel();
    }

    @Override
    public void detachMV() {
        mModel = null;
        mView = null;
    }

    @Override
    public void getDetailPlayer(int playerId, int bagPlayerId, LifecycleProvider rxLifecycle) {
        mModel.loadDetailPlayer(playerId, bagPlayerId, rxLifecycle, new Observer<HttpResult<DetailPlayer>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(HttpResult<DetailPlayer> detailPlayerHttpResult) {
                Log.d(TAG, "onNext: " + detailPlayerHttpResult);
                if (0 == detailPlayerHttpResult.getState()) {
                    mView.setDetailPlayer(detailPlayerHttpResult.getResult());
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
