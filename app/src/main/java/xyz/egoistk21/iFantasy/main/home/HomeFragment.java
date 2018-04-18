package xyz.egoistk21.iFantasy.main.home;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseFragment;
import xyz.egoistk21.iFantasy.main.recruit.RecruitFragment;
import xyz.egoistk21.iFantasy.util.DBUtil;

public class HomeFragment extends BaseFragment implements HomeContract.View {

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

    private HomeContract.Presenter mPresenter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initEvent() {
    }

    @OnClick(R.id.v_recruit)
    void recruit() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main, RecruitFragment.newInstance())
                .addToBackStack("recruit")
                .commit();
    }

    @Override
    protected void initData() {
        mPresenter = new HomePresenter(HomeFragment.this);
        tvNickname.setText(DBUtil.getUser().getNickname());
        tvLevel.setText(String.format(getResources().getString(R.string.level), DBUtil.getUser().getLevel()));
        tvVipLevel.setText(String.format(getResources().getString(R.string.vip_level), DBUtil.getUser().getVipLevel()));
        tvMoney.setText(String.format(getResources().getString(R.string.money), DBUtil.getUser().getMoney()));
    }

    @Override
    protected void lazyFetchData() {

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
