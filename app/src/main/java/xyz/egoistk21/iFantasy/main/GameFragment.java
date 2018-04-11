package xyz.egoistk21.iFantasy.main;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseFragment;
import xyz.egoistk21.iFantasy.util.DBUtil;

public class GameFragment extends BaseFragment implements GameContract.View {

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

    public static GameFragment newInstance() {
        return new GameFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_game;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initEvent() {
    }

    @Override
    protected void lazyFetchData() {
        tvNickname.setText(DBUtil.getUser().getNickname());
        tvLevel.setText(String.valueOf(DBUtil.getUser().getLevel()));
        tvVipLevel.setText(String.valueOf(DBUtil.getUser().getVipLevel()));
        tvMoney.setText(String.valueOf(DBUtil.getUser().getMoney()));
        mPresenter = new GamePresenter(GameFragment.this);
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
