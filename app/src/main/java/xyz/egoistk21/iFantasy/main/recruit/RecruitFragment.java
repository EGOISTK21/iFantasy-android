package xyz.egoistk21.iFantasy.main.recruit;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zxl.library.DropDownMenu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.OnClick;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseFragment;
import xyz.egoistk21.iFantasy.bean.RecruitInfo;
import xyz.egoistk21.iFantasy.bean.RecruitResult;
import xyz.egoistk21.iFantasy.main.gallery.GalleryFragment;
import xyz.egoistk21.iFantasy.util.DBUtil;
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
    @BindView(R.id.ddm_recruit)
    DropDownMenu ddmRecruit;
    @BindView(R.id.vp_recruit)
    NoScrollViewPager vpRecruit;

    private boolean luckyFree;
    private TimeCounter timeCounter;
    private String[] titles = new String[]{"ALL", "C", "PF", "SF", "SG", "PG",};
    private String[] types = new String[]{"位置", "评分", "薪资",};
    private Fragment[] fragments = new Fragment[titles.length];
    private MyFragmentStatePagerAdapter mPagerAdapter;
    private int mType = 0;
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
        for (int i = 0; i < fragments.length; i++) {
            Bundle bundle = new Bundle();
            bundle.putInt("pos", i);
            bundle.putInt("type", mType);
            fragments[i] = GalleryFragment.newInstance();
            fragments[i].setArguments(bundle);
        }
        vpRecruit.setAdapter(mPagerAdapter = new MyFragmentStatePagerAdapter());
    }

    @Override
    protected void initEvent() {
        tblRecruit.setupWithViewPager(vpRecruit);
    }

    @OnClick(R.id.tv_back)
    void back() {
        getFragmentManager().popBackStack();
    }

    @OnClick(R.id.btn_lucky_recruit)
    void luckyRecruit() {
        mPresenter.luckyRecruit(DBUtil.getUser().getId(), this);
    }

    @OnClick(R.id.btn_penta_lucky_recruit)
    void pentaLuckyRecruit() {
        mPresenter.pentaLuckyRecruit(DBUtil.getUser().getId(), this);
    }

    public int getType() {
        return mType;
    }

    @Override
    protected void initData() {
        mPresenter = new RecruitPresenter(this);
        tvMoney.setText(String.format(getResources().getString(R.string.money), DBUtil.getUser().getMoney()));
    }

    @Override
    protected void lazyFetchData() {
        mPresenter.getRecruitInfo(DBUtil.getUser().getId(), this);
    }

    @Override
    public void setMoney(int refresh) {
        tvMoney.setText(String.format(getResources().getString(R.string.money), DBUtil.refreshMoney(refresh)));
    }

    private void showLuckyRecruitFree() {
        luckyFree = true;
        tvOneRecruitTime.setText("");
        tvOneRecruitCost.setText("免费");
    }

    private void showLuckyRecruitUnfree(int time) {
        luckyFree = false;
        if (timeCounter == null) {
            timeCounter = new TimeCounter(time, 1000);
            timeCounter.start();
        }
        tvOneRecruitCost.setText(R.string.one_lucky_recruit_price);
    }

    @Override
    public void setRecruitInfo(RecruitInfo recruitInfo) {
        tvOneRecruitMsg.setText(String.format(getResources().getString(R.string.one_recruit), recruitInfo.getNum()));
        int time = recruitInfo.getTime() * 1000;
        if (time != 0) {
            showLuckyRecruitUnfree(time);
        } else {
            showLuckyRecruitFree();
        }
    }

    @Override
    public void showLuckyRecruitResult(RecruitResult recruitResult) {
        if (!luckyFree) setMoney(-100);
        mPresenter.getRecruitInfo(DBUtil.getUser().getId(), this);
    }

    @Override
    public void showPentaLuckyRecruitResult(ArrayList<RecruitResult> recruitResults) {
        setMoney(-400);
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
            showLuckyRecruitFree();
        }
    }

    private class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

        private GalleryFragment mCurrentFragment;

        MyFragmentStatePagerAdapter() {
            super(RecruitFragment.this.getChildFragmentManager());
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            mCurrentFragment = (GalleryFragment) object;
            super.setPrimaryItem(container, position, object);
        }

        public GalleryFragment getCurrentFragment() {
            return mCurrentFragment;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
