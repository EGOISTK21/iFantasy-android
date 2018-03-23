package xyz.egoistk21.iFantasy.login;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import xyz.egoistk21.iFantasy.util.APIUtil;

import static xyz.egoistk21.iFantasy.util.APIUtil.FILTER_TIMEOUT;

/**
 * Created by egoistk21 on 2018/3/22.
 */

class LoginModel implements LoginContract.Model {
    @Override
    public void getCode() {

    }

    @Override
    public void login(String phone, String zone, String code, Observer observer) {
        APIUtil.getVerifyCodeAPI().verify(phone, zone, code)
                .debounce(FILTER_TIMEOUT, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(observer);

    }
}
