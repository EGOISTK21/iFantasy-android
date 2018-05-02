package xyz.egoistk21.iFantasy.main.team;

import android.support.design.widget.TabLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.adapter.DropMenuAdapter;
import xyz.egoistk21.iFantasy.adapter.OnFilterDoneListener;
import xyz.egoistk21.iFantasy.base.BaseFragment;
import xyz.egoistk21.iFantasy.util.DBUtil;
import xyz.egoistk21.iFantasy.widget.DropDownMenu;
import xyz.egoistk21.iFantasy.widget.NoScrollViewPager;

public class TeamFragment extends BaseFragment implements TeamContract.View {

    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tbl_team)
    TabLayout tblTeam;
    @BindView(R.id.ddm_team)
    DropDownMenu ddmTeam;
    @BindView(R.id.vp_team)
    NoScrollViewPager vpTeam;

    private String[] mTitles = new String[]{"ALL", "C", "PF", "SF", "SG", "PG",};
    private List<String> mTypes = new ArrayList<>(Arrays.asList("位置", "评分", "薪资"));


    public static TeamFragment newInstance() {
        return new TeamFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_team;
    }

    @Override
    protected void initView() {
        ddmTeam.setMenuAdapter(new DropMenuAdapter(getContext(), new String[]{"位置"}, new OnFilterDoneListener() {
            @Override
            public void onFilterDone(int i, String s, String s1) {
//                mType = mTypes.indexOf(s1);
//                mPagerAdapter.getCurrentFragment().refreshSimplePlayers(mType);
                ddmTeam.setCurrentIndicatorText(s1);
                ddmTeam.close();
            }
        }));
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
