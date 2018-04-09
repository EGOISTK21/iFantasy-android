package xyz.egoistk21.iFantasy.game;

import android.view.View;
import android.widget.ProgressBar;

import butterknife.BindView;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseActivity;
import xyz.egoistk21.iFantasy.util.UIUtil;

public class GameActivity extends BaseActivity implements GameContract.View {

    private static final String TAG = GameActivity.class.getName();

    @BindView(R.id.game_progress)
    ProgressBar mPB;

    private GameContract.Presenter mPresenter;

    @Override
    protected void initData() {
        mPresenter = new GamePresenter(GameActivity.this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_game;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        UIUtil.hideNav(this);
    }

    @Override
    protected void onDetachP() {
        mPresenter.detachMV();
    }

    @Override
    public void showPB() {
        mPB.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissPB() {
        mPB.setVisibility(View.GONE);
    }
}
