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

    private boolean isPhoneChanged, isCounting, isDisplayPhoneForm;
    private String phone, code;
    private TimeCounter timeCounter;
    private VerifyContract.Presenter mPresenter;

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

    void getCode() {
//        SMSSDK.registerEventHandler(new EventHandler() {
//            public void afterEvent(int event, int result, Object data) {
//                if (result == SMSSDK.RESULT_COMPLETE) {
//                    boolean smart = (Boolean) data;
//                    if (smart) {
//                        //通过Mob云验证
//                    } else {
//                        //依然走短信验证
//                        // TODO 处理成功得到验证码的结果
//                        // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
//                    }
//                } else {
//                    // TODO 处理错误的结果
//                }
//
//            }
//        });
//                SMSSDK.getVerificationCode("86", phone);
    }

    @OnClick(R.id.btn_get_code)
    void send() {
        if (DBUtil.verifyPhone(phone)) {
            if (isPhoneChanged) {
                getCode();
            }
            go2CodeView();
        } else {
            ToastUtil.show("请输入13位中国大陆手机号");
        }
    }

    @OnClick(R.id.btn_verify)
    void verify() {
        if (DBUtil.verifyCode(code)) {
            mPresenter.startVerify(phone, "86", code, VerifyActivity.this);
        } else {
            ToastUtil.show("请输入4位有效验证码");
        }
    }

    @OnClick(R.id.tv_resend)
    void resend() {
        getCode();
        timeCounter.start();
    }

    @Override
    protected void initData() {
        isPhoneChanged = true;
        isCounting = false;
        isDisplayPhoneForm = true;
        timeCounter = new TimeCounter(60000, 1000);
        mPresenter = new VerifyPresenter(this);
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
    protected void onDetachP() {
        timeCounter.cancel();
        mPresenter.detachMV();
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!isDisplayPhoneForm && keyCode == KeyEvent.KEYCODE_BACK) {
            back2PhoneView();
            return true;// return true;拦截事件传递,从而屏蔽back键。
        }
        return super.onKeyDown(keyCode, event);
    }

    private class TimeCounter extends CountDownTimer {

        TimeCounter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Spannable spannable = new SpannableString(String.format(getResources().getString(R.string.verify_wait), phone, millisUntilFinished / 1000));
            spannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(VerifyActivity.this,
                    R.color.colorPrimaryDark)), 19, 22, Spanned.SPAN_POINT_MARK);
            tvResend.setText(spannable);
        }

        @Override
        public void onFinish() {
            Spannable spannable = new SpannableString(String.format(getResources().getString(R.string.verify_resend), phone));
            spannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(VerifyActivity.this,
                    R.color.colorPrimaryDark)), 19, 23, Spanned.SPAN_POINT_MARK);
            tvResend.setText(spannable);
        }
    }

}
