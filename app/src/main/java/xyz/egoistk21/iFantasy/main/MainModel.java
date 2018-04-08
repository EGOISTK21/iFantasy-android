package xyz.egoistk21.iFantasy.main;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.User;
import xyz.egoistk21.iFantasy.util.ApiUtil;
import xyz.egoistk21.iFantasy.util.DBUtil;

import static xyz.egoistk21.iFantasy.util.ApiUtil.FILTER_TIMEOUT;

/**
 * Created by EGOISTK21 on 2018/3/22.
 */

class MainModel implements MainContract.Model {
    @Override
    public boolean isLogin() {
        return DBUtil.getLoginToken() != null;
    }

    @Override
    public boolean isOnline() {
        return true;
    }

    @Override
    public void register(String phone, String nickname, RxAppCompatActivity rxAppCompatActivity, Observer<HttpResult<User>> observer) {
        ApiUtil.getRegisterApi().register(DBUtil.getLoginToken(), phone, nickname)
                .debounce(FILTER_TIMEOUT, TimeUnit.SECONDS)
                .compose(rxAppCompatActivity.<HttpResult<User>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    @Override
    public void login(String phone, RxAppCompatActivity rxAppCompatActivity, Observer<HttpResult<User>> observer) {
        ApiUtil.getLoginApi().login(DBUtil.getLoginToken(), phone)
                .debounce(FILTER_TIMEOUT, TimeUnit.SECONDS)
                .compose(rxAppCompatActivity.<HttpResult<User>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
