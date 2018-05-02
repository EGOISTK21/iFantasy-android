package xyz.egoistk21.iFantasy.main.team;

import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseFragment;
import xyz.egoistk21.iFantasy.util.DBUtil;

public class TeamFragment extends BaseFragment implements TeamContract.View {

    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.tv_money)
    TextView tvMoney;

    public static TeamFragment newInstance() {
        return new TeamFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_team;
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
        tvMoney.setText(String.format(getResources().getString(R.string.money), DBUtil.getUser().getMoney()));
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
