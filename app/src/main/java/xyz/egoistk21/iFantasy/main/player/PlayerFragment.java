package xyz.egoistk21.iFantasy.main.player;

import android.os.Bundle;
import android.util.Log;

import butterknife.OnClick;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseFragment;

public class PlayerFragment extends BaseFragment implements PlayerContract.View {

    private int mPlayerId = 2544;
    private int mBagPlayerId = 0;

    public static PlayerFragment newInstance() {
        return new PlayerFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_player_detail;
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
        Bundle bundle = getArguments();
        if (bundle != null) {
            mPlayerId = bundle.getInt("player_id");
            mBagPlayerId = bundle.getInt("bag_player_id");
        }
        Log.d(TAG, "initData: " + mPlayerId + " " + mBagPlayerId);
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
