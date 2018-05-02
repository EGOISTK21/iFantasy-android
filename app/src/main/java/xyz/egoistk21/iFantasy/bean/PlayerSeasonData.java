package xyz.egoistk21.iFantasy.bean;

public class PlayerSeasonData {

    private double ast;
    private double blk;
    private double drtg;
    private String efg_pct;
    private String fg3_pct;
    private String fg_pct;
    private String ft_pct;
    private int gp;
    private double min;
    private double ortg;
    private double pts;
    private int reb;
    private String season;
    private double stl;
    private String team_name;
    private double tov;
    private String ts_pct;

    public double getAst() {
        return ast;
    }

    public double getBlk() {
        return blk;
    }

    public double getDrtg() {
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

    public double getMin() {
        return min;
    }

    public double getOrtg() {
        return ortg;
    }

    public double getPts() {
        return pts;
    }

    public int getReb() {
        return reb;
    }

    public String getSeason() {
        return season;
    }

    public double getStl() {
        return stl;
    }

    public String getTeam_name() {
        return team_name;
    }

    public double getTov() {
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
