package xyz.egoistk21.iFantasy.main.recruit;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.RawPlayer;
import xyz.egoistk21.iFantasy.bean.RecruitInfo;
import xyz.egoistk21.iFantasy.bean.User;
import xyz.egoistk21.iFantasy.util.ApiUtil;

import static xyz.egoistk21.iFantasy.util.ApiUtil.FILTER_TIMEOUT;

class RecruitModel implements RecruitContract.Model {

    @Override
    public void getRecruitInfo(int user_id, LifecycleProvider rxLifecycle, Observer<HttpResult<RecruitInfo>> observer) {
        ApiUtil.getRecruitInfoApi().getRecruitInfo(user_id)
                .debounce(FILTER_TIMEOUT, TimeUnit.SECONDS)
                .compose(rxLifecycle.<HttpResult<User>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    @Override
    public void getPlayers(int pos, int type, LifecycleProvider rxLifecycle, Observer<HttpResult<ArrayList<RawPlayer>>> observer) {
        ApiUtil.getRecruitShowPlayerApi().showPlayer(pos, type)
                .debounce(FILTER_TIMEOUT, TimeUnit.SECONDS)
                .compose(rxLifecycle.<HttpResult<User>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
