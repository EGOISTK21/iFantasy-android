package xyz.egoistk21.iFantasy.main.recruit;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.OnClick;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.adapter.DropMenuAdapter;
import xyz.egoistk21.iFantasy.adapter.OnFilterDoneListener;
import xyz.egoistk21.iFantasy.base.BaseFragment;
import xyz.egoistk21.iFantasy.bean.RecruitInfo;
import xyz.egoistk21.iFantasy.bean.RecruitResult;
import xyz.egoistk21.iFantasy.main.gallery.GalleryFragment;
import xyz.egoistk21.iFantasy.util.DBUtil;
import xyz.egoistk21.iFantasy.widget.DropDownMenu;
import xyz.egoistk21.iFantasy.widget.LuckyDialog;
import xyz.egoistk21.iFantasy.widget.MyFragmentPagerAdapter;
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
    private int mType;
    private TimeCounter timeCounter;
    private String[] mTitles = new String[]{"ALL", "C", "PF", "SF", "SG", "PG",};
    private List<String> mTypes = new ArrayList<>(Arrays.asList("默认", "评分", "薪资"));
    private BaseFragment[] mFragments = new GalleryFragment[mTitles.length];
    private MyFragmentPagerAdapter mPagerAdapter;
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
        for (int i = 0; i < mFragments.length; i++) {
            Bundle bundle = new Bundle();
            bundle.putInt("pos", i);
            mFragments[i] = GalleryFragment.newInstance();
            mFragments[i].setArguments(bundle);
        }
        vpRecruit.setAdapter(mPagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), mTitles, mFragments));
        ddmRecruit.setMenuAdapter(new DropMenuAdapter(getContext(), new String[]{"默认"}, mTypes, new OnFilterDoneListener() {
            @Override
            public void onFilterDone(int i, String s, String s1) {
                mType = mTypes.indexOf(s1);
                mPagerAdapter.getCurrentFragment().refreshSimplePlayers(mType);
                ddmRecruit.setCurrentIndicatorText(s1);
                ddmRecruit.close();
            }
        }));
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
        new LuckyDialog
                .Builder(getActivity(), R.layout.dialog_lucky)
                .setTitle(R.string.lucky_recruit)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setRecruitResult(recruitResult)
                .create()
                .show();
    }

    @Override
    public void showPentaLuckyRecruitResult(ArrayList<RecruitResult> recruitResults) {
        setMoney(-400);
        new LuckyDialog
                .Builder(getActivity(), R.layout.dialog_lucky)
                .setTitle(R.string.lucky_recruit)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setRecruitResults(recruitResults)
                .create()
                .show();
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

}
