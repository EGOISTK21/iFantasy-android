package xyz.egoistk21.iFantasy.main.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.EditText;

import butterknife.OnClick;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseFragment;

import static cn.jpush.im.android.api.JMessageClient.createSingleTextMessage;

public class ChatFragment extends BaseFragment {

    private int mType = 0;
    private EditText FriendIDText;
    private String[] titles = new String[]{"世界频道", "好友频道"};
    private BaseFragment[] fragments = new  BaseFragment[titles.length];

    private class FragmentMessagePagerAdapter extends FragmentPagerAdapter {
        FragmentMessagePagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
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

    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat;
    }

    @Override
    protected void initView() {
        TabLayout tbl = rootView.findViewById(R.id.tab_layout);
        ViewPager viewPager = rootView.findViewById(R.id.viewpager);
        for (int i = 0; i < fragments.length; i++) {
            Bundle bundle = new Bundle();
            bundle.putInt("pus", i);
            if (i == 0) {
                fragments[i] = WorldFragment.newInstance();
                fragments[i].setArguments(bundle);
            }
            if (i == 1) {
                fragments[i] = FriendFragment.newInstance();
                fragments[i].setArguments(bundle);
            }

        }
        PagerAdapter Fragmentadpter = new FragmentMessagePagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(Fragmentadpter);
        tbl.setupWithViewPager(viewPager);
    }

    @Override
    protected void initEvent() {
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