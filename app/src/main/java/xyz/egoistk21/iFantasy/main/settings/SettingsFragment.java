package xyz.egoistk21.iFantasy.main.settings;

import butterknife.OnClick;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseFragment;

public class SettingsFragment extends BaseFragment implements SettingsContract.View {


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

    @Override
    protected void initData() {

    }

    @Override
    protected void lazyFetchData() {

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
