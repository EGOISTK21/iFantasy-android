package xyz.egoistk21.iFantasy.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class DetailPlayer implements Parcelable {

    public static final Creator<DetailPlayer> CREATOR = new Creator<DetailPlayer>() {
        @Override
        public DetailPlayer createFromParcel(Parcel in) {
            return new DetailPlayer(in);
        }

        @Override
        public DetailPlayer[] newArray(int size) {
            return new DetailPlayer[size];
        }
    };
    private double armspan;
    private String birthday;
    private int cloth_num;
    private String country;
    private String draft;
    private double height;
    private String image_url;
    private String name;
    private String pos;
    private int price;
    private double reach_height;
    private int score;
    private String team_name;
    private int weight;
    private String contract;

    public DetailPlayer() {
    }

    private DetailPlayer(Parcel in) {
        armspan = in.readDouble();
        birthday = in.readString();
        cloth_num = in.readInt();
        country = in.readString();
        draft = in.readString();
        height = in.readDouble();
        image_url = in.readString();
        name = in.readString();
        pos = in.readString();
        price = in.readInt();
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

    public String getImage_url() {
        return image_url;
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
        dest.writeString(image_url);
        dest.writeString(name);
        dest.writeString(pos);
        dest.writeInt(price);
        dest.writeDouble(reach_height);
        dest.writeInt(score);
        dest.writeString(team_name);
        dest.writeInt(weight);
        dest.writeString(contract);
    }

    @Override
    public String toString() {
        return "DetailPlayer{" +
                "armspan=" + armspan +
                ", birthday='" + birthday + '\'' +
                ", cloth_num=" + cloth_num +
                ", country='" + country + '\'' +
                ", draft='" + draft + '\'' +
                ", height=" + height +
                ", image_url='" + image_url + '\'' +
                ", name='" + name + '\'' +
                ", pos='" + pos + '\'' +
                ", price=" + price +
                ", reach_height=" + reach_height +
                ", score=" + score +
                ", team_name='" + team_name + '\'' +
                ", weight=" + weight +
                ", contract='" + contract + '\'' +
                '}';
    }
}
