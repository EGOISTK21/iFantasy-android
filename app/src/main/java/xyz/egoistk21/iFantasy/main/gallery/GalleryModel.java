package xyz.egoistk21.iFantasy.main.gallery;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.ArrayList;
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
    public void getSimplePlayers(int pos, int type, LifecycleProvider rxLifecycle, Observer<HttpResult<ArrayList<SimplePlayer>>> observer) {
        ApiUtil.getRecruitSimplePlayersApi().showPlayer(pos, type)
                .debounce(FILTER_TIMEOUT, TimeUnit.SECONDS)
                .compose(rxLifecycle.<HttpResult<User>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
