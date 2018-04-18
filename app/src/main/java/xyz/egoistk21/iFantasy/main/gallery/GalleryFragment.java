package xyz.egoistk21.iFantasy.main.gallery;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseFragment;
import xyz.egoistk21.iFantasy.bean.RawPlayer;

public class GalleryFragment extends BaseFragment implements GalleryContract.View {

    @BindView(R.id.rv_gallery)
    RecyclerView rvGallery;

    private int pos;

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

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        pos = getArguments() != null ? getArguments().getInt("pos") : 0;
        mPresenter = GalleryPresenter.getInstance(pos, this);
    }

    @Override
    protected void lazyFetchData() {
        mPresenter.getRawPlayers(pos, 0, this);
    }

    @Override
    public void setRawPlayers(ArrayList<RawPlayer> rawPlayers) {

    }

    @Override
    protected void onDetachP() {
        mPresenter.detachMV(pos);
    }

    @Override
    public void showPB() {

    }

    @Override
    public void dismissPB() {

    }
}
