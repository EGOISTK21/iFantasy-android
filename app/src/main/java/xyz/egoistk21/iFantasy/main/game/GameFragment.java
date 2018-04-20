package xyz.egoistk21.iFantasy.main.game;

import butterknife.OnClick;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseFragment;

public class GameFragment extends BaseFragment implements GameContract.View {


    public static GameFragment newInstance() {
        return new GameFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_game;
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
