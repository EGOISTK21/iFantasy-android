package xyz.egoistk21.iFantasy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.bean.PlayerSeasonData;

public class PlayerSeasonDataAdapter extends RecyclerView.Adapter<PlayerSeasonDataAdapter.PlayerSeasonDataViewHolder> {

    private Context mContext;
    private List<PlayerSeasonData> mPlayerSeasonDatas;

    public PlayerSeasonDataAdapter() {
        mPlayerSeasonDatas = new ArrayList<>();
    }

    public PlayerSeasonDataAdapter(List<PlayerSeasonData> playerSeasonDatas) {
        mPlayerSeasonDatas = playerSeasonDatas;
    }

    public void setPlayerSeasonDatas(List<PlayerSeasonData> playerSeasonDatas) {
        mPlayerSeasonDatas = playerSeasonDatas;
    }

    @NonNull
    @Override
    public PlayerSeasonDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_player_season_data, parent, false);
        return new PlayerSeasonDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerSeasonDataViewHolder holder, int position) {
        holder.setPlayerSeasonData(mPlayerSeasonDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mPlayerSeasonDatas.size();
    }

    class PlayerSeasonDataViewHolder extends RecyclerView.ViewHolder {

        private TextView tvSeason;
        private TextView tvTeamName;
        private TextView tvGp;
        private TextView tvStart;
        private TextView tvMin;
        private TextView tvPts;
        private TextView tvReb;
        private TextView tvAst;
        private TextView tvStl;
        private TextView tvBlk;
        private TextView tvTov;
        private TextView tvFgPct;
        private TextView tvFg3Pct;
        private TextView tvFtPct;
        private TextView tvEfgPct;
        private TextView tvTsPct;
        private TextView tvOrtg;
        private TextView tvDrtg;
        private PlayerSeasonData mPlayerSeasonData;

        PlayerSeasonDataViewHolder(View itemView) {
            super(itemView);
            tvSeason = itemView.findViewById(R.id.tv_season);
            tvTeamName = itemView.findViewById(R.id.tv_team_name);
            tvGp = itemView.findViewById(R.id.tv_gp);
            tvStart = itemView.findViewById(R.id.tv_start);
            tvMin = itemView.findViewById(R.id.tv_min);
            tvPts = itemView.findViewById(R.id.tv_pts);
            tvReb = itemView.findViewById(R.id.tv_reb);
            tvAst = itemView.findViewById(R.id.tv_ast);
            tvStl = itemView.findViewById(R.id.tv_stl);
            tvBlk = itemView.findViewById(R.id.tv_blk);
            tvTov = itemView.findViewById(R.id.tv_tov);
            tvFgPct = itemView.findViewById(R.id.tv_fg_pct);
            tvFg3Pct = itemView.findViewById(R.id.tv_fg3_pct);
            tvFtPct = itemView.findViewById(R.id.tv_ft_pct);
            tvEfgPct = itemView.findViewById(R.id.tv_efg_pct);
            tvTsPct = itemView.findViewById(R.id.tv_ts_pct);
            tvOrtg = itemView.findViewById(R.id.tv_ortg);
            tvDrtg = itemView.findViewById(R.id.tv_drtg);
        }

        private void setPlayerSeasonData(PlayerSeasonData playerSeasonData) {
            mPlayerSeasonData = playerSeasonData;
            tvSeason.setText(mPlayerSeasonData.getSeason());
            tvTeamName.setText(mPlayerSeasonData.getTeam_name());
            tvGp.setText(String.valueOf(mPlayerSeasonData.getGp()));
            tvStart.setText(String.valueOf(mPlayerSeasonData.getGp()));
            tvMin.setText(String.valueOf(mPlayerSeasonData.getMin()));
            tvPts.setText(String.valueOf(mPlayerSeasonData.getPts()));
            tvReb.setText(String.valueOf(mPlayerSeasonData.getReb()));
            tvAst.setText(String.valueOf(mPlayerSeasonData.getAst()));
            tvStl.setText(String.valueOf(mPlayerSeasonData.getStl()));
            tvBlk.setText(String.valueOf(mPlayerSeasonData.getBlk()));
            tvTov.setText(String.valueOf(mPlayerSeasonData.getTov()));
            tvFgPct.setText(mPlayerSeasonData.getFg_pct());
            tvFg3Pct.setText(mPlayerSeasonData.getFg3_pct());
            tvFtPct.setText(mPlayerSeasonData.getFt_pct());
            tvEfgPct.setText(mPlayerSeasonData.getEfg_pct());
            tvTsPct.setText(mPlayerSeasonData.getTs_pct());
            tvOrtg.setText(String.valueOf(mPlayerSeasonData.getOrtg()));
            tvDrtg.setText(String.valueOf(mPlayerSeasonData.getDrtg()));
        }
    }
}
