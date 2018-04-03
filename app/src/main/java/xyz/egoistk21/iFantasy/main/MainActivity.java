package xyz.egoistk21.iFantasy.main;

import android.content.Intent;
import android.graphics.Point;
import android.widget.Button;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.OnClick;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseActivity;
import xyz.egoistk21.iFantasy.verify.VerifyActivity;
import xyz.egoistk21.iFantasy.service.BGMService;
import xyz.egoistk21.iFantasy.util.UIUtil;

public class MainActivity extends BaseActivity implements MainContract.View {

    public static final int REQUEST_VERIFY = 650;
    public static final int NEED_REGISTER = 350;
    public static final int START_LOGIN = 351;

    private MainContract.Presenter mPresenter;

    @BindView(R.id.btn_start)
    Button btnStart;

    @Override
    protected void initData() {
        mPresenter = new MainPresenter(this);
        Intent intent = new Intent(MainActivity.this, BGMService.class);
        intent.putExtra("name", "cant_stop");
        startService(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
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
        if (!mPresenter.isLogined()) {
            Intent intent = new Intent(MainActivity.this, VerifyActivity.class);
            startActivityForResult(intent, REQUEST_VERIFY);
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

    private void startRegister() {
        // todo
    }

    private void startLogin() {

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
