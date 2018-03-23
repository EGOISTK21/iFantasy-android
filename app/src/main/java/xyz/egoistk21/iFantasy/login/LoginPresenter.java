package xyz.egoistk21.iFantasy.login;

/**
 * Created by egoistk21 on 2018/3/22.
 */

class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.Model mModel;
    private LoginContract.View mView;

    LoginPresenter(LoginContract.View view) {
        attachMV(view);
    }

    @Override
    public void attachMV(LoginContract.View view) {
        mModel = new LoginModel();
        mView = view;
    }

    @Override
    public void detachMV() {
        mModel = null;
        mView = null;
    }

    @Override
    public void getCode() {

    }

    @Override
    public void login(String phone, String zone, String code) {

    }
}
