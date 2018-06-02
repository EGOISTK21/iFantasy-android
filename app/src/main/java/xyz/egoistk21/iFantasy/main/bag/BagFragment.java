package xyz.egoistk21.iFantasy.main.bag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import butterknife.BindView;
import butterknife.OnClick;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseFragment;
import xyz.egoistk21.iFantasy.widget.NoScrollViewPager;

public class BagFragment extends BaseFragment {

    @BindView(R.id.tbl_bag)
    TabLayout tblBag;
    @BindView(R.id.vp_bag)
    NoScrollViewPager vpBag;

    private String[] mTitles = new String[]{"道具", "碎片", "装备",};
    private BaseFragment[] mFragments = new BagGalleryFragment[mTitles.length];

    public static BagFragment newInstance() {
        return new BagFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bag;
    }

    @Override
    protected void initView() {
        for (int i = 0; i < mFragments.length; i++) {
            Bundle bundle = new Bundle();
            bundle.putInt("pos", i);
            mFragments[i] = BagGalleryFragment.newInstance();
            mFragments[i].setArguments(bundle);
        }
        vpBag.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });
    }

    @Override
    protected void initEvent() {
        tblBag.setupWithViewPager(vpBag);
    }

    @OnClick(R.id.tv_back)
    void back() {
        getFragmentManager().popBackStack();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void lazyFetchData() {

    }

    @Override
    protected void onDetachP() {

    }
}
