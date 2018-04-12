package xyz.egoistk21.iFantasy.main.recruit;

import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseFragment;
import xyz.egoistk21.iFantasy.util.DBUtil;

public class RecruitFragment extends BaseFragment implements RecruitContract.View {

    @BindView(R.id.tv_money)
    TextView tvMoney;

    public static RecruitFragment newInstance() {
        return new RecruitFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recruit;
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
    protected void lazyFetchData() {
        tvMoney.setText(String.valueOf(DBUtil.getUser().getMoney()));
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
