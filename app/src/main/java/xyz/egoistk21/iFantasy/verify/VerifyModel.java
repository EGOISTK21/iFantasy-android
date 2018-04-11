package xyz.egoistk21.iFantasy.verify;

import com.trello.rxlifecycle2.components.RxActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import xyz.egoistk21.iFantasy.bean.HttpResult;
import xyz.egoistk21.iFantasy.bean.User;
import xyz.egoistk21.iFantasy.util.ApiUtil;

import static xyz.egoistk21.iFantasy.util.ApiUtil.FILTER_TIMEOUT;

/**
 * Created by egoistk21 on 2018/3/22.
 */

class VerifyModel implements VerifyContract.Model {

    @Override
    public void login(String phone, String zone, String code, RxActivity rxActivity, Observer<HttpResult<User>> observer) {
        ApiUtil.getVerifyCodeAPI().verify(phone, zone, code)
                .debounce(FILTER_TIMEOUT, TimeUnit.SECONDS)
                .compose(rxActivity.<HttpResult<User>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(observer);

    }
}
