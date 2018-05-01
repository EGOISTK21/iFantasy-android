package xyz.egoistk21.iFantasy.bean;

public class PlayerSeasonData {

    private double ast;
    private double blk;
    private double drtg;
    private double efg_pct;
    private double fg3_pct;
    private double fg_pct;
    private double ft_pct;
    private int gp;
    private double min;
    private double ortg;
    private double pts;
    private int reb;
    private String season;
    private double stl;
    private String team_name;
    private double tov;
    private double ts_pct;

    public double getAst() {
        return ast;
    }

    public double getBlk() {
        return blk;
    }

    public double getDrtg() {
        return drtg;
    }

    public double getEfg_pct() {
        return efg_pct;
    }

    public double getFg3_pct() {
        return fg3_pct;
    }

    public double getFg_pct() {
        return fg_pct;
    }

    public double getFt_pct() {
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

    public double getTs_pct() {
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
