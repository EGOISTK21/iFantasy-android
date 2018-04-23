package xyz.egoistk21.iFantasy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.bean.RawPlayer;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

    private static RequestOptions sRequestOptions =
            new RequestOptions()
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
    private Context mContext;
    private List<RawPlayer> mRawPlayers;

    public void setRawPlayers(List<RawPlayer> rawPlayers) {
        mRawPlayers = rawPlayers;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.gallery_item, parent, false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        holder.setData(mRawPlayers.get(position));
    }

    @Override
    public int getItemCount() {
        return mRawPlayers.size();
    }

    class GalleryViewHolder extends RecyclerView.ViewHolder {

        //        @BindView(R.id.iv_major)
//        ImageView ivMajor;
        @BindView(R.id.iv_player)
        ImageView ivPlayer;
        @BindView(R.id.tv_player)
        TextView tvPlayer;

        private RawPlayer mRawPlayer;

        GalleryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void setData(RawPlayer rawPlayer) {
            mRawPlayer = rawPlayer;
            Glide.with(mContext)
                    .load("file:///android_asset/" + rawPlayer.getId() + "/pic.webp")
                    .apply(sRequestOptions)
                    .into(ivPlayer);
            tvPlayer.setText(mRawPlayer.getName());
        }

        @OnClick(R.id.item_player)
        void showDetail() {
            // todo 根据mRawPlayer.getId()加载detail页面
        }
    }
}
