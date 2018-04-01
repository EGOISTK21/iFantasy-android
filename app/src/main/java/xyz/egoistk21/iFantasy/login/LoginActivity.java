package xyz.egoistk21.iFantasy.login;

import android.graphics.Point;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseActivity;
import xyz.egoistk21.iFantasy.util.DBUtil;
import xyz.egoistk21.iFantasy.util.UIUtil;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    private Boolean isDisplayPhoneForm;
    private String phone, code;
    private LoginContract.Presenter mPresenter;

    @BindView(R.id.ll_phone_login_form)
    LinearLayout llPhoneLogin;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.btn_get_code)
    Button btnGetCode;
    @BindView(R.id.ll_code_login_form)
    LinearLayout llCodeLogin;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void initData() {
        isDisplayPhoneForm = true;
        mPresenter = new LoginPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    private void modifyUI(LinearLayout linearLayout) {
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        int topMargin = UIUtil.px2dip(this, point.y / 3);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
        layoutParams.topMargin = topMargin;
        linearLayout.setLayoutParams(layoutParams);
    }

    @Override
    protected void initView() {
        modifyUI(llPhoneLogin);
        modifyUI(llCodeLogin);
    }

    @Override
    protected void initEvent() {

    }

    @OnTextChanged(R.id.et_phone)
    void getPhone(Editable editable) {
        phone = editable.toString();
    }

    @OnTextChanged(R.id.et_code)
    void getCode(Editable editable) {
        code = editable.toString();
    }

    @OnClick(R.id.btn_get_code)
    void getCode() {
        if (DBUtil.verifyPhone(phone)) {
//            SMSSDK.getVerificationCode("86", phone);
            go2VerificationCodeView();
        }
    }

    @OnClick(R.id.btn_login)
    void login() {
        mPresenter.login(phone, "86", code);
    }

    @Override
    public void go2VerificationCodeView() {
        UIUtil.slideOutToLeft(LoginActivity.this, llPhoneLogin);
        llPhoneLogin.setVisibility(View.GONE);
        llCodeLogin.setVisibility(View.VISIBLE);
        UIUtil.slideInFromRight(LoginActivity.this, llCodeLogin);
        isDisplayPhoneForm = false;
        etCode.requestFocus();
        if (!TextUtils.isEmpty(code)) {
            etCode.setSelection(code.length());
        }
    }

    @Override
    public void back2PhoneView() {
        UIUtil.slideOutToRight(LoginActivity.this, llCodeLogin);
        llCodeLogin.setVisibility(View.GONE);
        llPhoneLogin.setVisibility(View.VISIBLE);
        UIUtil.slideInFromLeft(LoginActivity.this, llPhoneLogin);
        isDisplayPhoneForm = true;
        etPhone.requestFocus();
        if (!TextUtils.isEmpty(phone)) {
            etPhone.setSelection(phone.length());
        }
    }

    @Override
    protected void onDetachP() {
        mPresenter.detachMV();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!isDisplayPhoneForm && keyCode == KeyEvent.KEYCODE_BACK) {
            back2PhoneView();
            return true;// return true;拦截事件传递,从而屏蔽back键。
        }
        return super.onKeyDown(keyCode, event);
    }

}
