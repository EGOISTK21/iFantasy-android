package xyz.egoistk21.iFantasy.main.player;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseFragment;
import xyz.egoistk21.iFantasy.bean.DetailPlayer;

public class DetailFragment extends BaseFragment {

    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_country)
    TextView tvCountry;
    @BindView(R.id.tv_body_measure)
    TextView tvBodyMeasure;
    @BindView(R.id.tv_draft)
    TextView tvDraft;
    @BindView(R.id.tv_contract)
    TextView tvContract;
    @BindView(R.id.ll_contract)
    LinearLayout llContract;
    private DetailPlayer mDetailPlayer;

    public static DetailFragment newInstance() {
        return new DetailFragment();
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

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mDetailPlayer = bundle.getParcelable("detail_player");
        }
        if (mDetailPlayer != null) {
            tvBirthday.setText(mDetailPlayer.getBirthday());
            tvCountry.setText(mDetailPlayer.getCountry());
            tvBodyMeasure.setText(String.format(getResources().getString(R.string.body_measure), mDetailPlayer.getHeight(), mDetailPlayer.getArmspan(),
                    mDetailPlayer.getReach_height(), mDetailPlayer.getWeight()));
            tvDraft.setText(mDetailPlayer.getDraft());
            if (TextUtils.isEmpty(mDetailPlayer.getContract())) {
                llContract.setVisibility(View.INVISIBLE);
            } else {
                tvContract.setText(mDetailPlayer.getContract());
            }
        }
    }

    @Override
    protected void lazyFetchData() {

    }

    @Override
    protected void onDetachP() {

    }
}
