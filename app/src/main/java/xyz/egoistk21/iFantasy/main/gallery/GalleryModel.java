package xyz.egoistk21.iFantasy.main.gallery;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.SimplePlayer;
import xyz.egoistk21.iFantasy.bean.User;
import xyz.egoistk21.iFantasy.util.ApiUtil;

import static xyz.egoistk21.iFantasy.util.ApiUtil.FILTER_TIMEOUT;

class GalleryModel implements GalleryContract.Model {
    @Override
    public void getRecruitSimplePlayers(int userId, int pos, int order, LifecycleProvider rxLifecycle, Observer<HttpResult<List<SimplePlayer>>> observer) {
        ApiUtil.getRecruitSimplePlayersApi().showPlayer(userId, pos, order)
                .debounce(FILTER_TIMEOUT, TimeUnit.SECONDS)
                .compose(rxLifecycle.<HttpResult<User>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    @Override
    public void getTeamSimplePlayers(int userId, int pos, int order, LifecycleProvider rxLifecycle, Observer<HttpResult<List<SimplePlayer>>> observer) {
        ApiUtil.getTeamPlayerApi().showPlayer(userId, pos, order)
                .debounce(FILTER_TIMEOUT, TimeUnit.SECONDS)
                .compose(rxLifecycle.<HttpResult<User>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
