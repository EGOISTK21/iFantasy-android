package xyz.egoistk21.iFantasy.welcome;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.OnClick;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseActivity;
import xyz.egoistk21.iFantasy.main.MainContainerActivity;
import xyz.egoistk21.iFantasy.service.BGMService;
import xyz.egoistk21.iFantasy.util.DBUtil;
import xyz.egoistk21.iFantasy.util.ToastUtil;
import xyz.egoistk21.iFantasy.util.UIUtil;
import xyz.egoistk21.iFantasy.verify.VerifyActivity;

public class WelcomeActivity extends BaseActivity implements WelcomeContract.View {

    public static final int REQUEST_VERIFY = 650;
    public static final int NEED_REGISTER = 350;
    public static final int START_LOGIN = 351;

    private WelcomeContract.Presenter mPresenter;

    @BindView(R.id.main_progress)
    ProgressBar mPB;
    @BindView(R.id.btn_start)
    Button btnStart;

    @Override
    protected void initData() {
        mPresenter = new WelcomePresenter(this);
        Intent intent = new Intent(WelcomeActivity.this, BGMService.class);
        intent.putExtra("name", "cant_stop");
        startService(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        int bottomMargin = UIUtil.px2dip(this, point.y / 3);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) btnStart.getLayoutParams();
        layoutParams.bottomMargin = bottomMargin;
        btnStart.setLayoutParams(layoutParams);
    }

    @Override
    protected void initEvent() {

    }

    @OnClick(R.id.btn_start)
    void start() {
        if (!mPresenter.isLogin()) {
            Intent intent = new Intent(WelcomeActivity.this, VerifyActivity.class);
            startActivityForResult(intent, REQUEST_VERIFY);
        } else {
            startLogin();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        UIUtil.hideNav(this);
    }

    @Override
    protected void onDetachP() {
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

    private void startRegister() {
        final EditText editText = new EditText(WelcomeActivity.this);
        editText.setGravity(View.TEXT_ALIGNMENT_CENTER);
        new AlertDialog.Builder(WelcomeActivity.this)
                .setTitle("请输入玩家昵称")
                .setView(editText)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String phone = DBUtil.getUser().getPhone();
                        String nickname = editText.getEditableText().toString();
                        if (!TextUtils.isEmpty(nickname)) {
                            mPresenter.register(phone, nickname, WelcomeActivity.this);
                        } else {
                            ToastUtil.show("昵称不能为空");
                        }
                    }
                }).show();
    }

    private void startLogin() {
        String phone = DBUtil.getUser().getPhone();
        mPresenter.login(phone, WelcomeActivity.this);
    }

    @Override
    public void startGame() {
        Intent intent = new Intent(WelcomeActivity.this, MainContainerActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_VERIFY) {
            switch (resultCode) {
                case NEED_REGISTER:
                    startRegister();
                    break;
                case START_LOGIN:
                    startLogin();
                    break;
            }
        }
    }
}
