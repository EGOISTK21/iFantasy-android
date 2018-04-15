package xyz.egoistk21.iFantasy.main.recruit;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseFragment;
import xyz.egoistk21.iFantasy.util.DBUtil;
import xyz.egoistk21.iFantasy.widget.GalleryFragment;
import xyz.egoistk21.iFantasy.widget.NoScrollViewPager;

public class RecruitFragment extends BaseFragment implements RecruitContract.View {

    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tbl_recruit)
    TabLayout tblRecruit;
    @BindView(R.id.vp_recruit)
    NoScrollViewPager vpRecruit;

    private Context mContext;

    public static RecruitFragment newInstance() {
        return new RecruitFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recruit;
    }

    @Override
    protected void initView() {
        mContext = getContext();
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
        tvMoney.setText(String.valueOf(DBUtil.getUser().getMoney()));
    }

    @Override
    protected void onDetachP() {

    }

    @Override
    public void showPB() {

    }

    @Override
    public void dismissPB() {

    }
}
