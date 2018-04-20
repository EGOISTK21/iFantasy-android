package xyz.egoistk21.iFantasy.main.bag;

import butterknife.OnClick;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseFragment;

public class BagFragment extends BaseFragment {

    public static BagFragment newInstance() {
        return new BagFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bag;
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
}
