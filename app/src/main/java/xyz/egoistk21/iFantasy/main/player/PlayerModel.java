package xyz.egoistk21.iFantasy.main.player;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.PlayerDetail;
import xyz.egoistk21.iFantasy.bean.User;
import xyz.egoistk21.iFantasy.util.ApiUtil;

import static xyz.egoistk21.iFantasy.util.ApiUtil.FILTER_TIMEOUT;

class PlayerModel implements PlayerContract.Model {
    @Override
    public void loadPlayerDetail(int playerId, int bagPlayerId, LifecycleProvider rxLifecycle, Observer<HttpResult<PlayerDetail>> observer) {
        ApiUtil.getPlayerDetailApi().query(playerId, bagPlayerId)
                .debounce(FILTER_TIMEOUT, TimeUnit.SECONDS)
                .compose(rxLifecycle.<HttpResult<User>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
