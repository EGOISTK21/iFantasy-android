package xyz.egoistk21.iFantasy.main.gallery;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import butterknife.BindView;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.adapter.GalleryAdapter;
import xyz.egoistk21.iFantasy.base.BaseFragment;
import xyz.egoistk21.iFantasy.bean.SimplePlayer;
import xyz.egoistk21.iFantasy.main.player.PlayerFragment;
import xyz.egoistk21.iFantasy.main.player.PlayerInterface;
import xyz.egoistk21.iFantasy.util.DBUtil;

public class GalleryFragment extends BaseFragment implements GalleryContract.View, PlayerInterface {

    @BindView(R.id.rv_gallery)
    RecyclerView rvGallery;

    static boolean mIsRecruit;
    private int mPos = 0;
    private static int mType = 0;

    private FragmentManager mParentFragmentManager;
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
        mParentFragmentManager = getParentFragment().getFragmentManager();
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
            mIsRecruit = bundle.getBoolean("isRecruit");
            mPos = bundle.getInt("pos");
        }
        mPresenter = GalleryPresenter.getInstance(mPos, this);
        Log.d(TAG, "initData: " + mPos + " " + mType);
    }

    @Override
    protected void lazyFetchData() {
        mPresenter.getSimplePlayers(DBUtil.getUser().getId(), mPos, mType, this);
    }

    public void refreshSimplePlayers(int type) {
        mPresenter.getSimplePlayers(DBUtil.getUser().getId(), mPos, mType = type, this);
        mGalleryAdapter.notifyDataSetChanged();
    }

    @Override
    public void setSimplePlayers(List<SimplePlayer> simplePlayers) {
        mGalleryAdapter.setSimplePlayers(simplePlayers);
        mGalleryAdapter.notifyDataSetChanged();
    }

    @Override
    public void go2PlayerDetail(int id, int bagId) {
        PlayerFragment fragment = PlayerFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putInt("bag_id", bagId);
        fragment.setArguments(bundle);
        mParentFragmentManager.beginTransaction()
                .replace(R.id.container_main, fragment)
                .addToBackStack(mIsRecruit ? "recruit" : "team")
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
