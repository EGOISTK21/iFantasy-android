package xyz.egoistk21.iFantasy.main.tactics;

import butterknife.OnClick;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseFragment;

public class TacticsFragment extends BaseFragment implements TacticsContract.View {


    public static TacticsFragment newInstance() {
        return new TacticsFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tactics;
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
