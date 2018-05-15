package xyz.egoistk21.iFantasy.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class PlayerDetail implements Parcelable {

    public static final Creator<PlayerDetail> CREATOR = new Creator<PlayerDetail>() {
        @Override
        public PlayerDetail createFromParcel(Parcel in) {
            return new PlayerDetail(in);
        }

        @Override
        public PlayerDetail[] newArray(int size) {
            return new PlayerDetail[size];
        }
    };
    private double armspan;
    private String birthday;
    private int cloth_num;
    private String country;
    private String draft;
    private double height;
    private String name;
    private String pos;
    private int price;
    private int salary;
    private double reach_height;
    private int score;
    private String team_name;
    private int weight;
    private String contract;

    public PlayerDetail() {
    }

    private PlayerDetail(Parcel in) {
        armspan = in.readDouble();
        birthday = in.readString();
        cloth_num = in.readInt();
        country = in.readString();
        draft = in.readString();
        height = in.readDouble();
        name = in.readString();
        pos = in.readString();
        price = in.readInt();
        salary = in.readInt();
        reach_height = in.readDouble();
        score = in.readInt();
        team_name = in.readString();
        weight = in.readInt();
        contract = in.readString();
    }

    public double getArmspan() {
        return armspan;
    }

    public String getBirthday() {
        return birthday;
    }

    public int getCloth_num() {
        return cloth_num;
    }

    public String getCountry() {
        return country;
    }

    public String getDraft() {
        return draft;
    }

    public double getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public String getPos() {
        return pos;
    }

    public int getPrice() {
        return price;
    }

    public int getSalary() {
        return salary;
    }

    public double getReach_height() {
        return reach_height;
    }

    public int getScore() {
        return score;
    }

    public String getTeam_name() {
        return team_name;
    }

    public int getWeight() {
        return weight;
    }

    public String getContract() {
        return contract;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(armspan);
        dest.writeString(birthday);
        dest.writeInt(cloth_num);
        dest.writeString(country);
        dest.writeString(draft);
        dest.writeDouble(height);
        dest.writeString(name);
        dest.writeString(pos);
        dest.writeInt(price);
        dest.writeInt(salary);
        dest.writeDouble(reach_height);
        dest.writeInt(score);
        dest.writeString(team_name);
        dest.writeInt(weight);
        dest.writeString(contract);
    }

    @Override
    public String toString() {
        return "PlayerDetail{" +
                "armspan=" + armspan +
                ", birthday='" + birthday + '\'' +
                ", cloth_num=" + cloth_num +
                ", country='" + country + '\'' +
                ", draft='" + draft + '\'' +
                ", height=" + height +
                ", name='" + name + '\'' +
                ", pos='" + pos + '\'' +
                ", price=" + price +
                ", salary=" + salary +
                ", reach_height=" + reach_height +
                ", score=" + score +
                ", team_name='" + team_name + '\'' +
                ", weight=" + weight +
                ", contract='" + contract + '\'' +
                '}';
    }
}
