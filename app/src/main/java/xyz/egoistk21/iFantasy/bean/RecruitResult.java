package xyz.egoistk21.iFantasy.bean;

public class RecruitResult {

    protected String type;
    protected String name;
    protected String pic;
    protected int num;

    public String getType() {
        return type;
    }

    public Class getClazz() {
        switch (type) {
            case "player":
                return RecruitedPlayer.class;
            case "trail":
                return TrialCard.class;
            case "piece":
                return PlayerPiece.class;
            case "fund":
                return CoinCard.class;
            case "exp":
                return ExpCard.class;
            default:
                return RecruitResult.class;
        }
    }

    @Override
    public String toString() {
        return "RecruitResult{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                ", num=" + num +
                '}';
    }
}
