package xyz.egoistk21.iFantasy.main.settings;

import android.app.Activity;
import android.content.Intent;

import butterknife.OnClick;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseFragment;
import xyz.egoistk21.iFantasy.util.DBUtil;
import xyz.egoistk21.iFantasy.welcome.WelcomeActivity;

public class SettingsFragment extends BaseFragment implements SettingsContract.View {

    private SettingsContract.Presenter mPresenter;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {

    }

    @OnClick(R.id.tv_back)
    void back() {
        getFragmentManager().popBackStack();
    }

    @OnClick(R.id.btn_logout)
    void logout() {
        mPresenter.logout(DBUtil.getUser().getId(), this);
    }

    @Override
    protected void initData() {
        mPresenter = new SettingsPresenter(this);
    }

    @Override
    protected void lazyFetchData() {

    }

    @Override
    protected void onDetachP() {
        mPresenter.detachMV();
    }

    @Override
    public void onLogout() {
        Activity main = getActivity();
        Intent intent = new Intent(main, WelcomeActivity.class);
        main.startActivity(intent);
        main.finish();
    }

    @Override
    public void showPB() {

    }

    @Override
    public void dismissPB() {

    }
}
