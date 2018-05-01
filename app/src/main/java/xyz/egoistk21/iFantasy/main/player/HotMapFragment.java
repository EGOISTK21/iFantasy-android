package xyz.egoistk21.iFantasy.main.player;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseFragment;
import xyz.egoistk21.iFantasy.widget.PlayerDialog;

public class HotMapFragment extends BaseFragment {

    @BindView(R.id.iv_hot_map)
    ImageView ivHotMap;

    private String image_url;

    public static HotMapFragment newInstance() {
        return new HotMapFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_player_hot_map;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initEvent() {

    }

    @OnClick(R.id.iv_hot_map)
    void show() {
        new PlayerDialog
                .Builder(getActivity(), R.layout.dialog_hot_map)
                .setImageUrl(image_url)
                .create()
                .show();
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            image_url = bundle.getString("hot_map_player");
        }
        if (!TextUtils.isEmpty(image_url)) {
            image_url = "file:///android_asset/" + image_url + "/hot_map.webp";
            Glide.with(getContext())
                    .load(image_url)
                    .into(ivHotMap);
        }
    }

    @Override
    protected void lazyFetchData() {

    }

    @Override
    protected void onDetachP() {

    }
}
