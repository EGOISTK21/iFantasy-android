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
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseActivity;
import xyz.egoistk21.iFantasy.util.UIUtil;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    private Boolean isDisplayPhoneForm;
    private String phone, code;
    private LoginContract.Presenter mPresenter;

    @BindView(R.id.ll_phone_login_form)
    LinearLayout llPhoneLoginForm;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.btn_get_code)
    Button btnGetCode;
    @BindView(R.id.ll_code_login_form)
    LinearLayout llCodeLoginForm;
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
        modifyUI(llPhoneLoginForm);
        modifyUI(llCodeLoginForm);
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

    private Boolean verifyPhone() {
        // "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
        return !TextUtils.isEmpty(phone) && phone.matches(telRegex);
    }

    @OnClick(R.id.btn_get_code)
    void getCode() {
        if (verifyPhone()) {
            // 注册一个事件回调，用于处理发送验证码操作的结果
            SMSSDK.registerEventHandler(new EventHandler() {
                public void afterEvent(int event, int result, Object data) {
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        // TODO 处理成功得到验证码的结果
                        // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                        UIUtil.slideOutToLeft(LoginActivity.this, llPhoneLoginForm);
                        llPhoneLoginForm.setVisibility(View.GONE);
                        llCodeLoginForm.setVisibility(View.VISIBLE);
                        UIUtil.slideInFromRight(LoginActivity.this, llCodeLoginForm);
                        isDisplayPhoneForm = false;
                    } else {
                        // TODO 处理错误的结果
                    }

                }
            });
            // 触发操作
            SMSSDK.getVerificationCode("86", phone);
        } else {

        }
    }

    @OnClick(R.id.btn_login)
    void login() {

    }

    void back2PhoneForm() {
        UIUtil.slideOutToRight(this, llCodeLoginForm);
        llCodeLoginForm.setVisibility(View.GONE);
        llPhoneLoginForm.setVisibility(View.VISIBLE);
        UIUtil.slideInFromLeft(this, llPhoneLoginForm);
        isDisplayPhoneForm = true;
    }

    @Override
    protected void onDetachP() {
        mPresenter.detachMV();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!isDisplayPhoneForm && keyCode == KeyEvent.KEYCODE_BACK) {
            back2PhoneForm();
            return true;// return true;拦截事件传递,从而屏蔽back键。
        }
        return super.onKeyDown(keyCode, event);
    }

}
