package xyz.egoistk21.iFantasy.main.bag;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import butterknife.BindView;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.adapter.BagGalleryAdapter;
import xyz.egoistk21.iFantasy.base.BaseFragment;
import xyz.egoistk21.iFantasy.main.gallery.GalleryFragment;

public class BagGalleryFragment extends BaseFragment implements BagGalleryContract.View {
    @BindView(R.id.rv_gallery)
    RecyclerView rvGallery;

    private int mPos = 0;

    private BagGalleryAdapter mGalleryAdapter;
    private GridLayoutManager mGridLayoutManager;
    private BagGalleryContract.Presenter mPresenter;

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
            mGalleryAdapter = new BagGalleryAdapter(this);
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
        }
        mPresenter = BagGalleryPresenter.getInstance(mPos, this);
        Log.d(TAG, "initData: " + mPos);
    }

    @Override
    protected void lazyFetchData() {
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
