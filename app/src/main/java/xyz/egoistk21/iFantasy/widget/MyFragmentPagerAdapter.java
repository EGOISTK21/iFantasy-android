package xyz.egoistk21.iFantasy.widget;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import xyz.egoistk21.iFantasy.base.BaseFragment;
import xyz.egoistk21.iFantasy.main.gallery.GalleryFragment;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles;
    private BaseFragment[] mFragments;
    private GalleryFragment mCurrentFragment;

    public MyFragmentPagerAdapter(FragmentManager fragmentManager, String[] titles, BaseFragment[] fragments) {
        super(fragmentManager);
        mTitles = titles;
        mFragments = fragments;
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
}
