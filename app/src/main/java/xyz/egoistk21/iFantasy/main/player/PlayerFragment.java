package xyz.egoistk21.iFantasy.main.player;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseFragment;
import xyz.egoistk21.iFantasy.bean.DetailPlayer;
import xyz.egoistk21.iFantasy.widget.CircleImageView;

public class PlayerFragment extends BaseFragment implements PlayerContract.View {

    @BindView(R.id.civ_avatar)
    CircleImageView civAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_team)
    TextView tvTeam;
    @BindView(R.id.tv_cloth)
    TextView tvCloth;
    @BindView(R.id.tv_pos)
    TextView tvPos;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.tv_salary)
    TextView tvSalary;
    @BindView(R.id.btn_play_off)
    Button btnPlayOff;
    @BindView(R.id.btn_dismissal)
    Button btnDismissal;

    private int mPlayerId;
    private int mBagPlayerId;

    private PlayerContract.Presenter mPresenter;

    public static PlayerFragment newInstance() {
        return new PlayerFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_player;
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
        mPresenter = new PlayerPresenter(this);
        Log.d(TAG, "initData: " + mPlayerId + " " + mBagPlayerId);
    }

    @Override
    protected void lazyFetchData() {
        mPresenter.getDetailPlayer(mPlayerId, mBagPlayerId, this);
    }

    @Override
    protected void onDetachP() {
        mPresenter.detachMV();
    }

    @Override
    public void setDetailPlayer(DetailPlayer detailPlayer) {
        Glide.with(getContext())
                .load("file:///android_asset/" + detailPlayer.getImage_url() + "/pic.webp")
                .into(civAvatar);
        tvName.setText(detailPlayer.getName());
        tvTeam.setText(String.format(getResources().getString(R.string.team_name), detailPlayer.getTeam_name()));
        tvCloth.setText(String.format(getResources().getString(R.string.cloth_num), detailPlayer.getCloth_num()));
        tvPos.setText(String.format(getResources().getString(R.string.pos), detailPlayer.getPos()));
        tvScore.setText(String.format(getResources().getString(R.string.score), detailPlayer.getScore()));
        tvSalary.setText(String.format(getResources().getString(R.string.salary), detailPlayer.getPrice()));
    }

    @Override
    public void showPB() {

    }

    @Override
    public void dismissPB() {

    }
}
