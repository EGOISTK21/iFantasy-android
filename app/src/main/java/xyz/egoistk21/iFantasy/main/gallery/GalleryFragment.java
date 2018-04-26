package xyz.egoistk21.iFantasy.main.gallery;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import butterknife.BindView;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.adapter.GalleryAdapter;
import xyz.egoistk21.iFantasy.base.BaseFragment;
import xyz.egoistk21.iFantasy.bean.SimplePlayer;

public class GalleryFragment extends BaseFragment implements GalleryContract.View {

    @BindView(R.id.rv_gallery)
    RecyclerView rvGallery;

    private int mPos;
    private int mType;

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
        if (mGalleryAdapter == null) {
            mGalleryAdapter = new GalleryAdapter();
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
        mPos = getArguments() != null ? getArguments().getInt("pos") : 0;
        mType = getArguments() != null ? getArguments().getInt("type") : 0;
        mPresenter = GalleryPresenter.getInstance(mPos, this);
        Log.d(TAG, "initData: " + mPos + " " + mType);
    }

    @Override
    protected void lazyFetchData() {
        mPresenter.getRawPlayers(mPos, mType, this);
    }

    public void refreshRawPlayers(int type) {
        mPresenter.getRawPlayers(mPos, mType = type, this);
    }

    @Override
    public void setRawPlayers(ArrayList<SimplePlayer> simplePlayers) {
        mGalleryAdapter.setSimplePlayers(simplePlayers);
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
