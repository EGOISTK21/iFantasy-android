package xyz.egoistk21.iFantasy.main;

import cn.jpush.android.api.JPushInterface;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseActivity;
import xyz.egoistk21.iFantasy.util.UIUtil;

public class MainContainerActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_container_main;
    }

    @Override
    protected void initView() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main, GameFragment.newInstance())
                .addToBackStack("main")
                .commit();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        JPushInterface.setAlias(this, 1, "1");
    }

    @Override
    protected void onDetachP() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        UIUtil.hideNav(this);
    }
}
