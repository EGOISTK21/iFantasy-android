package xyz.egoistk21.iFantasy.main.player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseFragment;
import xyz.egoistk21.iFantasy.bean.DetailPlayer;
import xyz.egoistk21.iFantasy.widget.NoScrollViewPager;

public class PlayerFragment extends BaseFragment implements PlayerContract.View {

    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_team)
    TextView tvTeam;
    @BindView(R.id.tv_cloth)
    TextView tvCloth;
    @BindView(R.id.tv_pos)
    TextView tvPos;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.tv_salary)
    TextView tvSalary;
    @BindView(R.id.btn_play_off)
    Button btnPlayOff;
    @BindView(R.id.btn_dismissal)
    Button btnDismissal;
    @BindView(R.id.tbl_player)
    TabLayout tblPlayer;
    @BindView(R.id.vp_player)
    NoScrollViewPager vpPlayer;

    private int mPlayerId;
    private int mBagPlayerId;
    private String[] mTitles = new String[]{"资料", "投篮热图", "数据",};
    private Fragment[] mFragments = new BaseFragment[mTitles.length];
    private PlayerContract.Presenter mPresenter;

    public static PlayerFragment newInstance() {
        return new PlayerFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_player;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {
        tblPlayer.setupWithViewPager(vpPlayer);
    }

    @OnClick(R.id.tv_back)
    void back() {
        getFragmentManager().popBackStack();
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mPlayerId = bundle.getInt("player_id");
            mBagPlayerId = bundle.getInt("bag_player_id");
        }
        mPresenter = new PlayerPresenter(this);
        mPresenter.getDetailPlayer(mPlayerId, mBagPlayerId, this);
        mFragments[0] = DetailFragment.newInstance();
        mFragments[1] = HotMapFragment.newInstance();
        mFragments[2] = DataFragment.newInstance();
        Log.d(TAG, "initData: " + mPlayerId + " " + mBagPlayerId);
    }

    @Override
    protected void lazyFetchData() {
    }

    @Override
    protected void onDetachP() {
        mPresenter.detachMV();
    }

    @Override
    public void setDetailPlayer(DetailPlayer detailPlayer) {
        Bundle bundle0 = new Bundle();
        bundle0.putParcelable("detail_player", detailPlayer);
        mFragments[0].setArguments(bundle0);
        Bundle bundle1 = new Bundle();
        bundle1.putString("hot_map_player", detailPlayer.getImage_url());
        mFragments[1].setArguments(bundle1);
        vpPlayer.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

            @Override
            public int getCount() {
                return mFragments.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });

        Glide.with(getContext())
                .load("file:///android_asset/" + detailPlayer.getImage_url() + "/pic.webp")
                .into(ivAvatar);
        tvName.setText(detailPlayer.getName());
        tvTeam.setText(String.format(getResources().getString(R.string.team_name), detailPlayer.getTeam_name()));
        tvCloth.setText(String.format(getResources().getString(R.string.cloth_num), detailPlayer.getCloth_num()));
        tvPos.setText(String.format(getResources().getString(R.string.pos), detailPlayer.getPos()));
        tvScore.setText(String.format(getResources().getString(R.string.score), detailPlayer.getScore()));
        tvSalary.setText(String.format(getResources().getString(R.string.salary), detailPlayer.getPrice()));
    }

    @Override
    public void showPB() {

    }

    @Override
    public void dismissPB() {

    }
}
