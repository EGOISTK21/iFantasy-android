package xyz.egoistk21.iFantasy.main.recruit;

import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.OnClick;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseFragment;
import xyz.egoistk21.iFantasy.bean.RawPlayer;
import xyz.egoistk21.iFantasy.bean.RecruitInfo;
import xyz.egoistk21.iFantasy.bean.RecruitResult;
import xyz.egoistk21.iFantasy.util.DBUtil;
import xyz.egoistk21.iFantasy.widget.GalleryFragment;
import xyz.egoistk21.iFantasy.widget.NoScrollViewPager;

public class RecruitFragment extends BaseFragment implements RecruitContract.View {

    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_one_recruit_time)
    TextView tvOneRecruitTime;
    @BindView(R.id.tv_one_recruit_msg)
    TextView tvOneRecruitMsg;
    @BindView(R.id.tv_one_recruit_cost)
    TextView tvOneRecruitCost;
    @BindView(R.id.tbl_recruit)
    TabLayout tblRecruit;
    @BindView(R.id.vp_recruit)
    NoScrollViewPager vpRecruit;

    private TimeCounter timeCounter;
    private RecruitContract.Presenter mPresenter;


    public static RecruitFragment newInstance() {
        return new RecruitFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recruit;
    }

    @Override
    protected void initView() {
        vpRecruit.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {

            private CharSequence[] titles = new CharSequence[]{
                    "ALL",
                    "C",
                    "PF",
                    "SF",
                    "SG",
                    "PG",
            };

            private Fragment[] fragments = new Fragment[]{
                    GalleryFragment.newInstance(),
                    GalleryFragment.newInstance(),
                    GalleryFragment.newInstance(),
                    GalleryFragment.newInstance(),
                    GalleryFragment.newInstance(),
                    GalleryFragment.newInstance(),
            };

            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return 6;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
    }

    @Override
    protected void initEvent() {
        tblRecruit.setupWithViewPager(vpRecruit);
    }

    @OnClick(R.id.tv_back)
    void back() {
        getFragmentManager().popBackStack();
    }

    @Override
    protected void lazyFetchData() {
        mPresenter = new RecruitPresenter(this);
        mPresenter.getRecruitInfo(DBUtil.getUser().getId(), this);
        mPresenter.getPlayers(0, 0, this);
        if (this.isAdded()) {
            tvMoney.setText(String.format(getResources().getString(R.string.money), DBUtil.getUser().getMoney()));
        }

    }

    @OnClick(R.id.btn_lucky_recruit)
    void luckyRecruit() {
        mPresenter.luckyRecruit(DBUtil.getUser().getId(), this);
    }

    @OnClick(R.id.btn_penta_lucky_recruit)
    void pentaLuckyRecruit() {
        mPresenter.pentaLuckyRecruit(DBUtil.getUser().getId(), this);
    }

    @Override
    public void setRecruitInfo(RecruitInfo recruitInfo) {
        if (isAdded()) {
            tvOneRecruitMsg.setText(String.format(getResources().getString(R.string.one_recruit), recruitInfo.getNum()));
        }
        int time = recruitInfo.getTime() * 1000;
        if (time != 0) {
            timeCounter = new TimeCounter(time, 1000);
            timeCounter.start();
            tvOneRecruitCost.setText("100万");
        } else {
            tvOneRecruitTime.setText("");
            tvOneRecruitCost.setText("免费");
        }
    }

    @Override
    public void setRawPlayers(ArrayList<RawPlayer> rawPlayers) {

    }

    @Override
    public void showLuckyRecruitResult(RecruitResult recruitResult) {
        mPresenter.getRecruitInfo(DBUtil.getUser().getId(), this);
    }

    @Override
    public void showPentaLuckyRecruitResult(ArrayList<RecruitResult> recruitResults) {

    }

    @Override
    protected void onDetachP() {
        mPresenter.detachMV();
    }

    @Override
    public void showPB() {

    }

    @Override
    public void dismissPB() {

    }

    private class TimeCounter extends CountDownTimer {

        private SimpleDateFormat mFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

        TimeCounter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (RecruitFragment.this.isAdded()) {
                tvOneRecruitTime.setText(String.format(getString(R.string.one_recruit_time),
                        mFormat.format(millisUntilFinished - TimeZone.getDefault().getRawOffset())));
            }
        }

        @Override
        public void onFinish() {
            tvOneRecruitTime.setText("");
        }
    }
}
