package xyz.egoistk21.iFantasy.main.gallery;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import butterknife.BindView;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.adapter.GalleryAdapter;
import xyz.egoistk21.iFantasy.base.BaseFragment;
import xyz.egoistk21.iFantasy.bean.SimplePlayer;
import xyz.egoistk21.iFantasy.main.player.PlayerFragment;

public class GalleryFragment extends BaseFragment implements GalleryContract.View {

    @BindView(R.id.rv_gallery)
    RecyclerView rvGallery;

    private int mPos = 0;
    private int mType = 0;

    private FragmentManager mFragmentManager;
    private GalleryAdapter mGalleryAdapter;
    private GridLayoutManager mGridLayoutManager;
    private GalleryContract.Presenter mPresenter;

    public static GalleryFragment newInstance() {
        return new GalleryFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gallery;
    }

    @Override
    protected void initView() {
        mFragmentManager = getParentFragment().getFragmentManager();
        if (mGalleryAdapter == null) {
            mGalleryAdapter = new GalleryAdapter(this);
            rvGallery.setAdapter(mGalleryAdapter);
        }
        if (mGridLayoutManager == null) {
            mGridLayoutManager = new GridLayoutManager(getContext(), 6);
        }
        rvGallery.setLayoutManager(mGridLayoutManager);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mPos = bundle.getInt("pos");
            mType = bundle.getInt("type");
        }
        mPresenter = GalleryPresenter.getInstance(mPos, this);
        Log.d(TAG, "initData: " + mPos + " " + mType);
    }

    @Override
    protected void lazyFetchData() {
        mPresenter.getSimplePlayers(mPos, mType, this);
    }

    public void refreshRawPlayers(int type) {
        mPresenter.getSimplePlayers(mPos, mType = type, this);
    }

    @Override
    public void setSimplePlayers(ArrayList<SimplePlayer> simplePlayers) {
        mGalleryAdapter.setSimplePlayers(simplePlayers);
    }

    @Override
    public void go2PlayerDetail(int playerId) {
        PlayerFragment fragment = PlayerFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putInt("player_id", playerId);
        fragment.setArguments(bundle);
        mFragmentManager.beginTransaction()
                .replace(R.id.container_main, fragment)
                .addToBackStack("recruit")
                .commit();
    }

    @Override
    protected void onDetachP() {
        mPresenter.detachMV(mPos);
    }

    @Override
    public void showPB() {

    }

    @Override
    public void dismissPB() {

    }
}
