package xyz.egoistk21.iFantasy.main.player;

import com.trello.rxlifecycle2.LifecycleProvider;

import io.reactivex.Observer;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.PlayerDetail;

interface PlayerContract {
    interface Model {
        void loadPlayerDetail(int playerId, int bagPlayerId, LifecycleProvider rxLifecycle, Observer<HttpResult<PlayerDetail>> observer);
    }

    interface View {
        void setPlayerDetail(PlayerDetail playerDetail);

        void showPB();

        void dismissPB();
    }

    interface Presenter {
        void attachMV(View view);

        void detachMV();

        void getPlayerDetail(int playerId, int bagPlayerId, LifecycleProvider rxLifecycle);
    }
}
