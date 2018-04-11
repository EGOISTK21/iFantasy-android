package xyz.egoistk21.iFantasy.verify;

import android.graphics.Point;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseActivity;
import xyz.egoistk21.iFantasy.util.DBUtil;
import xyz.egoistk21.iFantasy.util.ToastUtil;
import xyz.egoistk21.iFantasy.util.UIUtil;
import xyz.egoistk21.iFantasy.welcome.WelcomeActivity;

public class VerifyActivity extends BaseActivity implements VerifyContract.View {

    private boolean isPhoneChanged, isCounting, isDisplayPhoneForm;
    private String phone, code;
    private TimeCounter timeCounter;
    private VerifyContract.Presenter mPresenter;

    @BindView(R.id.verify_progress)
    ProgressBar mPB;
    @BindView(R.id.ll_phone_verify_form)
    LinearLayout llPhoneLogin;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.btn_get_code)
    Button btnGetCode;
    @BindView(R.id.ll_code_verify_form)
    LinearLayout llCodeLogin;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.btn_verify)
    Button btnVerify;
    @BindView(R.id.tv_resend)
    TextView tvResend;

    @Override
    protected void initData() {
        isPhoneChanged = true;
        isCounting = false;
        isDisplayPhoneForm = true;
        timeCounter = new TimeCounter(60000, 1000);
        mPresenter = new VerifyPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_verify;
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
        isPhoneChanged = !(editable.toString().equals(phone));
        phone = editable.toString();
    }

    @OnTextChanged(R.id.et_code)
    void getCode(Editable editable) {
        code = editable.toString();
    }

    @OnClick(R.id.btn_get_code)
    void getCode() {
        if (DBUtil.verifyPhone(phone)) {
            if (isPhoneChanged) {
//                SMSSDK.getVerificationCode("86", phone);
            }
            go2CodeView();
        } else {
            ToastUtil.show("请输入13位中国大陆手机号");
        }
    }

    @OnClick(R.id.btn_verify)
    void verify() {
        if (DBUtil.verifyCode(code)) {
            mPresenter.login(phone, "86", code, VerifyActivity.this);
        } else {
            ToastUtil.show("请输入4位有效验证码");
        }
    }

    @OnClick(R.id.tv_resend)
    void resend() {
        // SMSSDK.getVerificationCode("86", phone);
        timeCounter.start();
    }

    @Override
    public void go2CodeView() {
        UIUtil.slideOutToLeft(VerifyActivity.this, llPhoneLogin);
        llPhoneLogin.setVisibility(View.GONE);
        llCodeLogin.setVisibility(View.VISIBLE);
        UIUtil.slideInFromRight(VerifyActivity.this, llCodeLogin);
        if (isPhoneChanged) {
            if (isCounting) {
                timeCounter.cancel();
            }
            timeCounter.start();
        }
        isCounting = true;
        isDisplayPhoneForm = false;
        etCode.requestFocus();
        if (!TextUtils.isEmpty(code)) {
            etCode.setSelection(code.length());
        }
    }

    @Override
    public void back2PhoneView() {
        UIUtil.slideOutToRight(VerifyActivity.this, llCodeLogin);
        llCodeLogin.setVisibility(View.GONE);
        llPhoneLogin.setVisibility(View.VISIBLE);
        UIUtil.slideInFromLeft(VerifyActivity.this, llPhoneLogin);
        isPhoneChanged = false;
        isDisplayPhoneForm = true;
        etPhone.requestFocus();
        if (!TextUtils.isEmpty(phone)) {
            etPhone.setSelection(phone.length());
        }
    }

    @Override
    public void showPB() {
        mPB.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissPB() {
        mPB.setVisibility(View.GONE);
    }

    @Override
    public void go2Register() {
        setResult(WelcomeActivity.NEED_REGISTER);
        finish();
    }

    @Override
    public void go2Login() {
        setResult(WelcomeActivity.START_LOGIN);
        finish();
    }

    @Override
    protected void onDetachP() {
        mPresenter.detachMV();
    }

    private class TimeCounter extends CountDownTimer {

        TimeCounter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Spannable spannable = new SpannableString(String.format("验证码已发送至%s，%2ds后可再次获取", phone, millisUntilFinished / 1000));
            spannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(VerifyActivity.this,
                    R.color.colorPrimaryDark)), 19, 22, Spanned.SPAN_POINT_MARK);
            tvResend.setText(spannable);
        }

        @Override
        public void onFinish() {
            Spannable spannable = new SpannableString("验证码已发送至" + phone + "，重新获取");
            spannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(VerifyActivity.this,
                    R.color.colorPrimaryDark)), 19, 23, Spanned.SPAN_POINT_MARK);
            tvResend.setText(spannable);
        }
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
