package xyz.egoistk21.iFantasy.main;

import android.view.KeyEvent;

import cn.jpush.android.api.JPushInterface;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseActivity;
import xyz.egoistk21.iFantasy.main.home.HomeFragment;
import xyz.egoistk21.iFantasy.util.UIUtil;

public class MainContainerActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_container_main;
    }

    @Override
    protected void initView() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main, HomeFragment.newInstance())
                .addToBackStack("home")
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;// return true;拦截事件传递,从而屏蔽back键。
        }
        return super.onKeyDown(keyCode, event);
    }
}
