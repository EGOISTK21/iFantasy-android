package xyz.egoistk21.iFantasy.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseActivity;
import xyz.egoistk21.iFantasy.util.UIUtil;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    private LoginContract.Presenter mPresenter;

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void initData() {
        mPresenter = new LoginPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {

    }

    @OnClick(R.id.btn_login)
    void getCode() {
        UIUtil.slideOutToLeft(this, etPhone);
        etPhone.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onDetachP() {
        mPresenter.detachMV();
    }
}
