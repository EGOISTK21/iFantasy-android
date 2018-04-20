package xyz.egoistk21.iFantasy.main.home;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseFragment;
import xyz.egoistk21.iFantasy.main.bag.BagFragment;
import xyz.egoistk21.iFantasy.main.chat.ChatFragment;
import xyz.egoistk21.iFantasy.main.game.GameFragment;
import xyz.egoistk21.iFantasy.main.recruit.RecruitFragment;
import xyz.egoistk21.iFantasy.main.settings.SettingsFragment;
import xyz.egoistk21.iFantasy.main.tactics.TacticsFragment;
import xyz.egoistk21.iFantasy.main.team.TeamFragment;
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

    @OnClick(R.id.v_settings)
    void settings() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main, SettingsFragment.newInstance())
                .addToBackStack("settings")
                .commit();
    }

    @OnClick(R.id.v_chat)
    void chat() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main, ChatFragment.newInstance())
                .addToBackStack("message")
                .commit();
    }

    @OnClick(R.id.v_bag)
    void bag() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main, BagFragment.newInstance())
                .addToBackStack("bag")
                .commit();
    }

    @OnClick(R.id.v_recruit)
    void recruit() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main, RecruitFragment.newInstance())
                .addToBackStack("recruit")
                .commit();
    }

    @OnClick(R.id.v_tactics)
    void tactics() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main, TacticsFragment.newInstance())
                .addToBackStack("tactics")
                .commit();
    }

    @OnClick(R.id.v_team)
    void team() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main, TeamFragment.newInstance())
                .addToBackStack("team")
                .commit();
    }

    @OnClick(R.id.civ_game)
    void game() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main, GameFragment.newInstance())
                .addToBackStack("game")
                .commit();
    }

    @Override
    protected void initData() {
        mPresenter = new HomePresenter(HomeFragment.this);
        Glide.with(getContext()).load(R.drawable.avatar).into(civAvatar);
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
