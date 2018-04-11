package xyz.egoistk21.iFantasy.game;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;
import de.hdodenhof.circleimageview.CircleImageView;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseActivity;
import xyz.egoistk21.iFantasy.util.DBUtil;
import xyz.egoistk21.iFantasy.util.UIUtil;

public class GameActivity extends BaseActivity implements GameContract.View {

    private static final String TAG = GameActivity.class.getName();

    @BindView(R.id.game_progress)
    ProgressBar mPB;
    @BindView(R.id.civ_avatar)
    CircleImageView civAvatar;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.tv_vip_level)
    TextView tvVipLevel;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.tv_money)
    TextView tvMoney;

    private GameContract.Presenter mPresenter;

    @Override
    protected void initData() {
        JPushInterface.setAlias(GameActivity.this, 1, "1");
        mPresenter = new GamePresenter(GameActivity.this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_game;
    }

    @Override
    protected void initView() {
        tvNickname.setText(DBUtil.getUser().getNickname());
        tvLevel.setText(String.valueOf(DBUtil.getUser().getLevel()));
        tvVipLevel.setText(String.valueOf(DBUtil.getUser().getVipLevel()));
        tvMoney.setText(String.valueOf(DBUtil.getUser().getMoney()));
    }

    @Override
    protected void initEvent() {

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
}
