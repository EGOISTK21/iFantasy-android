package xyz.egoistk21.iFantasy.main.player;

import com.trello.rxlifecycle2.LifecycleProvider;

import io.reactivex.Observer;
import xyz.egoistk21.iFantasy.bean.DetailPlayer;
import xyz.egoistk21.iFantasy.bean.HttpResult;

interface PlayerContract {
    interface Model {
        void loadDetailPlayer(int playerId, int bagPlayerId, LifecycleProvider rxLifecycle, Observer<HttpResult<DetailPlayer>> observer);
    }

    interface View {
        void setDetailPlayer(DetailPlayer detailPlayer);

        void showPB();

        void dismissPB();
    }

    interface Presenter {
        void attachMV(View view);

        void detachMV();

        void getDetailPlayer(int playerId, int bagPlayerId, LifecycleProvider rxLifecycle);
    }
}
