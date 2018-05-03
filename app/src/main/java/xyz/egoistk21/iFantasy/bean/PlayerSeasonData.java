package xyz.egoistk21.iFantasy.bean;

public class PlayerSeasonData {

    private float ast;
    private float blk;
    private float drtg;
    private String efg_pct;
    private String fg3_pct;
    private String fg_pct;
    private String ft_pct;
    private int gp;
    private float min;
    private float ortg;
    private float pts;
    private float reb;
    private String season;
    private float stl;
    private String team_name;
    private float tov;
    private String ts_pct;

    public float getAst() {
        return ast;
    }

    public float getBlk() {
        return blk;
    }

    public float getDrtg() {
        return drtg;
    }

    public String getEfg_pct() {
        return efg_pct;
    }

    public String getFg3_pct() {
        return fg3_pct;
    }

    public String getFg_pct() {
        return fg_pct;
    }

    public String getFt_pct() {
        return ft_pct;
    }

    public int getGp() {
        return gp;
    }

    public float getMin() {
        return min;
    }

    public float getOrtg() {
        return ortg;
    }

    public float getPts() {
        return pts;
    }

    public float getReb() {
        return reb;
    }

    public String getSeason() {
        return season;
    }

    public float getStl() {
        return stl;
    }

    public String getTeam_name() {
        return team_name;
    }

    public float getTov() {
        return tov;
    }

    public String getTs_pct() {
        return ts_pct;
    }

    @Override
    public String toString() {
        return "PlayerSeasonData{" +
                "ast=" + ast +
                ", blk=" + blk +
                ", drtg=" + drtg +
                ", efg_pct=" + efg_pct +
                ", fg3_pct=" + fg3_pct +
                ", fg_pct=" + fg_pct +
                ", ft_pct=" + ft_pct +
                ", gp=" + gp +
                ", min=" + min +
                ", ortg=" + ortg +
                ", pts=" + pts +
                ", reb=" + reb +
                ", season='" + season + '\'' +
                ", stl=" + stl +
                ", team_name='" + team_name + '\'' +
                ", tov=" + tov +
                ", ts_pct=" + ts_pct +
                '}';
    }
}
