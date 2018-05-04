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

import java.util.ArrayList;
import java.util.List;

import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.bean.SimplePlayer;
import xyz.egoistk21.iFantasy.main.player.PlayerInterface;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

    private static RequestOptions sRequestOptions =
            new RequestOptions()
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
    private Context mContext;
    private List<SimplePlayer> mSimplePlayers;
    private PlayerInterface mIPlayer;

    public GalleryAdapter(PlayerInterface iPlayer) {
        mSimplePlayers = new ArrayList<>();
        mIPlayer = iPlayer;
    }

    public GalleryAdapter(List<SimplePlayer> simplePlayers, PlayerInterface iPlayer) {
        mSimplePlayers = simplePlayers;
        mIPlayer = iPlayer;
    }

    public void setSimplePlayers(List<SimplePlayer> simplePlayers) {
        mSimplePlayers = simplePlayers;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_gallery, parent, false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        holder.setData(mSimplePlayers.get(position));
    }

    @Override
    public int getItemCount() {
        return mSimplePlayers.size();
    }

    class GalleryViewHolder extends RecyclerView.ViewHolder {

        //      private ImageView ivMajor;
        private ImageView ivPlayer;
        private TextView tvPlayer;

        private SimplePlayer mSimplePlayer;

        GalleryViewHolder(View itemView) {
            super(itemView);
            ivPlayer = itemView.findViewById(R.id.iv_player);
            tvPlayer = itemView.findViewById(R.id.tv_player);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIPlayer.go2PlayerDetail(mSimplePlayer.getId(), mSimplePlayer.getBag_id());
                }
            });
        }

        private void setData(SimplePlayer simplePlayer) {
            mSimplePlayer = simplePlayer;
            Glide.with(mContext)
                    .load("file:///android_asset/" + mSimplePlayer.getId() + "/pic.webp")
                    .apply(sRequestOptions)
                    .into(ivPlayer);
            tvPlayer.setText(mSimplePlayer.getName());
        }
    }
}
