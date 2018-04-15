package xyz.egoistk21.iFantasy.widget;

import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import xyz.egoistk21.iFantasy.Adapter.GalleryAdapter;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseFragment;

public class GalleryFragment extends BaseFragment {

    @BindView(R.id.rv_gallery)
    RecyclerView rvGallery;

    public static GalleryFragment newInstance() {
        return new GalleryFragment();
    }

    public static GalleryFragment newInstance(GalleryAdapter galleryAdapter, RecyclerView.LayoutManager layoutManager) {
        GalleryFragment galleryFragment = new GalleryFragment();
        galleryFragment.rvGallery.setAdapter(galleryAdapter);
        galleryFragment.rvGallery.setLayoutManager(layoutManager);
        return galleryFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gallery;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void lazyFetchData() {

    }

    @Override
    protected void onDetachP() {

    }
}
